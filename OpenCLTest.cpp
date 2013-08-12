#include <CL/cl.h>
#include <stdio.h>
#include <stdlib.h>

int main(int argc, char **argv) {
    int i;
    cl_uint num_platforms;
    cl_platform_id *platforms;
    cl_platform_id *active = NULL;

    cl_int err = clGetPlatformIDs(0, NULL, &num_platforms);
    platforms = (cl_platform_id *)malloc(sizeof(cl_platform_id) * num_platforms);
    err = clGetPlatformIDs(num_platforms, platforms, 0);

    for(i = 0; i < num_platforms; i++) {
        cl_uint num_cpus, num_gpus;
        cl_platform_id *plat = platforms + i;
        err = clGetDeviceIDs(*plat, CL_DEVICE_TYPE_GPU, 0, NULL, &num_gpus);
        if (err == CL_DEVICE_NOT_FOUND) {
            num_gpus = 0;
        }
        err = clGetDeviceIDs(*plat, CL_DEVICE_TYPE_CPU, 0, NULL, &num_cpus);
        if (err == CL_DEVICE_NOT_FOUND) {
            num_cpus = 0;
        }
        printf("Platform %d has %d GPU(s), %d CPU(s)\n",i, num_gpus, num_cpus);

        if (active == NULL && num_gpus > 0) {
            active = plat;
        }
    }

    free(platforms);
}
