# install OS and web Server
# nginx is a combo of alpine OS and nginx we server
FROM nginx:latest

# change the WORKINGDIR of the container to point to the folder where html files are stored.
# this is similart to cd command
# nginx is generally available at /usr/share/nginx inside the alpine linux.
WORKDIR /usr/share/nginx/html

# copy app code to this directory
COPY index.html index.html
# also can be written as COPY . . (copies everything from current local machine to container's current working directory)

# nginx automatically exposes port 80 for us . so we dont need extra expose command 
# other servers might expose a different port. so it is better to checkout the documentation for the corresponding documentations.



