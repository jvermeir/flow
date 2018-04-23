import json
import sys

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
        paths.add(get_signature(path))


def get_signature(path):
    return ">".join([p["calling"] for p in path])


def is_last_message_of_path(line):
    return "contollerEnd" in line["type"]


def get_messages_from_log_file(log_file_name):
    with open(log_file_name, "r") as f:
        log_lines = f.readlines()
    messages = [json.loads(x) for x in log_lines]
    return messages


def main():
    if len(sys.argv) < 2:
        print "Usage: python analyze_log.py <full path to log file>"
        sys.exit(-1)

    log_file_name = sys.argv[1]
    print "processing logfile: " +  log_file_name
    partial_paths = {}
    paths = set()
    messages = get_messages_from_log_file(log_file_name)
    [add_line_to_result_map(line, partial_paths, paths) for line in messages]

    print(paths)

    res = ",".join([ '"' + path + '"' for path in paths])
    res = "{" + res + "}"
    print (res)


if __name__ == "__main__":
    main()