#!/bin/bash
echo test_sh
/usr/lib64/ccache/gcc -o /home/simon/java/network/server/hello_world_server /home/simon/java/network/server/hello_world_server.c
echo test_over
/home/simon/java/network/server/hello_world_server
echo execute is over
