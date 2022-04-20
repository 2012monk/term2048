#include "console.h"

int color_on() {
    if (has_colors()) {
        start_color();
        return 0;
    }
    return -1;
}

int quantize(int red, int green, int blue, int hex) {
    int power = 1;
    int shifts = 8;
    int result = hex;

    while (power < COLORS) {
        power <<= 3;
        shifts--;
    }

    if (shifts > 0) {
        red >>= shifts;
        green >>= shifts;
        blue >>= shifts;
        result = ((red << (2 * (8 - shifts)))
              + (green << (8 - shifts))
              + blue);
    }
    return result & (COLORS - 1);
}

int set_color(int hex) {
    if (hex < 0) return -1;
    int red   = (hex & 0xff0000) >> 16;
	int green = (hex & 0x00ff00) >> 8;
	int blue  = (hex & 0x0000ff) >> 0;
    int color = quantize(red, green, blue, hex);
    double scale = 1000 / 256;
    init_extended_color(color, red * scale,green * scale, blue * scale);
    return color;
}

int set_color_rgb(uint8_t r, uint8_t b, uint8_t g) {
    return set_color((r << 16) + (b << 8) + g);
}

int set_pair(int fg, int bg) {
    int cfg = set_color(fg);
    int cbg = set_color(bg);
    int pair = find_pair(cfg, cbg);
    if (pair != -1) {
        return pair;
    }
    return alloc_pair(cfg, cbg);
}

int set_color_attr(int fg, int bg) {
    int p = set_pair(fg, bg);
    attr_on(COLOR_PAIR(p), 0);
    return p;
}

void unset_color_attr(int p) {
    attr_off(COLOR_PAIR(p), 0);
}

