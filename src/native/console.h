#ifndef _XOPEN_SOURCE_EXTENDED
# define _XOPEN_SOURCE_EXTENDED 1
#endif
#include <ncurses.h>
#include <stdio.h>
#include <stdint.h>
#include <string.h>

#define PAIR 1
#define FG_PAIR 2
#define BG_PAIR 3

typedef struct _RGBColor {
    short color;
    uint8_t r;
    uint8_t g;
    uint8_t b;
} RGBColor;
extern RGBColor FG;
extern RGBColor BG;


#define _COLOR_ON(N) attr_on(COLOR_PAIR(N), 0)
#define _COLOR_OFF(N) attr_off(COLOR_PAIR(N), 0)
#define FG_ON _COLOR_ON(FG_PAIR)
#define FG_OFF _COLOR_OFF(FG_PAIR)
#define BG_ON _COLOR_ON(BG_PAIR)
#define BG_OFF _COLOR_OFF(BG_PAIR)
#define COLOR_ON _COLOR_ON(PAIR)
#define COLOR_OFF _COLOR_OFF(PAIR)
#define SET_PAIR(N) _COLOR_ON(N)
#define UNSET_PAIR(N) _COLOR_OFF(N)
#define OUT_256
#define OUT_RGB
#define OUT_16

int set_fg(uint8_t r, uint8_t b, uint8_t g);
int set_bg(uint8_t r, uint8_t b, uint8_t g);
int set_hex_fg(int hex);
int set_hex_bg(int hex);
int set_hex_pair(int fgHex, int bgHex);
int set_pair(uint8_t cfr, uint8_t cfb, uint8_t cfg, uint8_t cbr, uint8_t cbb, uint8_t cbg);
int get_pair(int fgHex, int bgHex);
int init_colors();
