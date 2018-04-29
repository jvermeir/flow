import json
import sys


class Segment:
    def __init__(self, method_name, class_name, phase, return_type):
        self.method_name = method_name
        self.class_name = class_name
        self.phase = phase
        self.return_type = return_type


def add_line_to_result_map(line, partial_paths, paths):
    thread = line['Thread']
    path = partial_paths.get(thread)
    if path is None:
        partial_paths[thread] = [line]
    else:
        partial_paths[thread].append(line)
    if is_last_message_of_path(line):
        path = partial_paths[thread]
        del partial_paths[thread]
        paths.add(get_callpath(path))


def get_callpath(path):
    return tuple([get_segment(p) for p in path])


def get_segment(segment):
    calling = segment["calling"].split(' ')
    method_call = calling[1][:-1]
    return_type = calling[0].split('(')[1]
    method_call_parts = method_call.split('.')
    num_parts = len(method_call_parts)
    return Segment(method_call_parts[num_parts - 1], method_call_parts[num_parts - 2], segment["phase"], return_type)


def is_last_message_of_path(line):
    return "controller" in line["type"] and "returning" in line["phase"]


def get_messages_from_log_file(log_file_name):
    with open(log_file_name, "r") as f:
        log_lines = f.readlines()
    messages = [json.loads(x) for x in log_lines]
    return messages


def print_method_calls(paths, output):
    for path in paths:
        current = Segment('ext', 'ext', 'calling', 'void')
        index = 1
        number_of_nodes = len(path) - 1
        for segment in path:
            if segment.phase == "calling":
                output.write(current.class_name + " -> " + segment.class_name + ":" + segment.method_name + "\n")
                current = segment
                index = index + 1
            else:
                if index > number_of_nodes:
                    output.write(segment.class_name + " --> ext: " + segment.return_type + "\n")
                else:
                    next = path[index]
                    output.write(current.class_name + " --> " + next.class_name + ":" + current.return_type + "\n")
                    current = segment
                    index = index + 1


def print_seq_diag(paths):
    with open("test.sd", "w") as output:
        output.write("@startuml\n")
        print_method_calls(paths, output)
        output.write("@enduml\n")


def main():
    if len(sys.argv) < 2:
        print "Usage: python analyze_log.py <full path to log file>"
        sys.exit(-1)

    log_file_name = sys.argv[1]
    print "processing logfile: " + log_file_name
    partial_paths = {}
    paths = set()
    messages = get_messages_from_log_file(log_file_name)
    [add_line_to_result_map(line, partial_paths, paths) for line in messages]
    print_seq_diag(paths)


if __name__ == "__main__":
    main()

