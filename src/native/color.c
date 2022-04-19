#include "console.h"

RGBColor FG = {51, 255,255,255};
RGBColor BG = {50, 0, 0, 0};

int init_colors() {
  if (!has_colors()) {
    return 0;
  }
    use_default_colors();
    start_color();
    int ret = init_extended_pair(PAIR, FG.color, BG.color);
    ret |= init_extended_pair(FG_PAIR, FG.color, -1);
    ret |= init_extended_pair(BG_PAIR, -1, BG.color);
    return ret;
}

int get_pair(int fgHex, int bgHex) {
    if (fgHex < 0) return FG_PAIR;
    if (bgHex < 0) return BG_PAIR;
    return PAIR;
}

int _set_color(RGBColor *color) {
    double scale = 256;
    int r = ((double) color->r / scale) * 1000;
    int g = ((double) color->g / scale) * 1000;
    int b = ((double) color->b / scale) * 1000;
    return init_extended_color(color->color, r,g,b);
}

int set_fg(uint8_t r, uint8_t b, uint8_t g) {
    FG.r=r;
    FG.b=b;
    FG.g=g;
    return _set_color(&FG);
}
int set_bg(uint8_t r, uint8_t b, uint8_t g) {
    BG.r=r;
    BG.b=b;
    BG.g=g;
    return _set_color(&BG);
}

void hex_to_rgb(int hex, uint8_t *r, uint8_t *g, uint8_t *b) {
    hex &= 0xFFFFFF;
    *r = (hex & 0xFF0000)>> 16;
    *g = (hex & 0x00FF00) >> 8;
    *b = (hex & 0x0000FF) >> 0;
}

int set_hex_fg(int hex) {
    uint8_t r,g,b;
    if (hex < 0) return 0;
    hex_to_rgb(hex, &r,&g,&b);
    return set_fg(r,g,b);
}
int set_hex_bg(int hex) {
    uint8_t r,g,b;
    if (hex < 0) return 0;
    hex_to_rgb(hex, &r,&g,&b);
    return set_bg(r,g,b);
}

int set_hex_pair(int fgHex, int bgHex) {
    return set_hex_fg(fgHex) | set_hex_bg(bgHex);
}

int set_pair(uint8_t cfr, uint8_t cfb, uint8_t cfg, uint8_t cbr, uint8_t cbb, uint8_t cbg) {
    return set_fg(cfr, cfb, cfg) | set_bg(cbr, cbb, cbg);
}

