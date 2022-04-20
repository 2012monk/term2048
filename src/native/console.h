#ifndef _XOPEN_SOURCE_EXTENDED
# define _XOPEN_SOURCE_EXTENDED 1
#endif
#include <ncurses.h>
#include <stdio.h>
#include <stdint.h>
#include <string.h>

#define OUT_256
#define OUT_RGB
#define OUT_16

int set_color(int hex);
int set_color_rgb(uint8_t r, uint8_t b, uint8_t g);
int color_on(void);
int set_color_attr(int fg, int bg);
void unset_color_attr(int p);
