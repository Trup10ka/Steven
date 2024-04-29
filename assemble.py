import os
import shutil

# This script is used to assemble the server and client resources into a single directory
def copy_directory_contents(source, destination):
    for root, dirs, files in os.walk(source):
        for dirElement in dirs:
            source_dir = os.path.join(root, dirElement)
            relative_dir = os.path.relpath(source_dir, source)
            destination_dir = os.path.join(destination, relative_dir)
            if not os.path.exists(destination_dir):
                os.makedirs(destination_dir)

        for fileElement in files:
            source_file = os.path.join(root, fileElement)
            relative_file = os.path.relpath(source_file, source)
            destination_file = os.path.join(destination, relative_file)
            shutil.copy2(source_file, destination_file)
            print(f"Copied: {source_file} -> {destination_file}")

            if fileElement == 'steven.js' or fileElement == 'steven.js.map':
                scripts_dir = os.path.join(destination, 'scripts')
                target_path = os.path.join(scripts_dir, fileElement)
                shutil.move(source_file, target_path)
                print(f"Moved: {source_file} -> {target_path}")


directory_name = "test-build"
current_dir = os.getcwd()

directory_path = os.path.join(current_dir, directory_name)

if not os.path.exists(directory_path):
    os.makedirs(directory_path)
    print(f"Directory '{directory_name}' created successfully.")
else:
    print(f"Directory '{directory_name}' already exists, skipping this part.")

# Output of Build
server_jar_path = os.path.join(current_dir, "server/build/libs/Steven-Server-1.0.0.jar")
client_resources_path = os.path.join(current_dir, "webapp/build/dist/js/productionExecutable")

# Testing directory
server_jar_destination = os.path.join(current_dir, "test-build")
server_resources_destination = os.path.join(current_dir, "test-build/resources")

if not os.path.exists(server_resources_destination):
    os.makedirs(server_resources_destination)
    print(f"Destination directory '{server_resources_destination}' was missing, creating new.")

server_file_name = os.path.basename(server_jar_path)

shutil.copy(server_jar_path, server_jar_destination)
print(f"File '{server_file_name}' copied to '{server_jar_destination}' successfully.")

# Recursively copy contents from client_resources_path to server_resources_destination
copy_directory_contents(client_resources_path, server_resources_destination)

batch_file = os.path.join(directory_path, "run.bat")

content = f"""
java -jar {server_file_name}
"""

with open(batch_file, 'w') as file:
    file.write(content)

print(f"Batch file '{batch_file}' created successfully.")
