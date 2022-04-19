#include "console.h"

#define SUCCESS 1
RGBColor FG;
RGBColor BG;

short scale_up(int c) {
    double s = 256;
    return ((double) c / s) * 1000;
}

int test_color(RGBColor *T, int (*f)(uint8_t,uint8_t,uint8_t)) {
    int r,g,b, result = SUCCESS;
    for (int k = 0; k < 3; k++) {
        for (int i = 0; i < 256; i++) {
            f(r,g,b);
            extended_color_content(T->color, &r, &g, &b);
            result &= scale_up(T->r)==r;
            result &= scale_up(T->b)==b;
            result &= scale_up(T->g)==g;
            if (result != SUCCESS) {
                printw("ERR  color %d == %d %d - %d %d - %d %d",T->color,  T->r,r,T->b,b,T->g,g);
                return result;
            }
        }
    }
    return result;
}

void test_fg(void) {
    if (test_color(&FG, &set_fg) != SUCCESS) {
        printw("FG TEST FAILD\n");
        return;
    }
    printw("FG TEST SUCCESS\n");
}

void test_bg(void) {
    if (test_color(&BG, &set_bg) != SUCCESS) {
        printw("BG TEST FAILD\n");
        return;
    }
    printw("BG TEST SUCCESS\n");
}

void test_pair() {
    int result;
    short pair;
    COLOR_ON;
    attr_get(0, &pair, 0);
    result &= pair == (PAIR);
    FG_ON;
    attr_get(0, &pair, 0);
    result &= pair == (FG_PAIR);
    BG_ON;
    attr_get(0, &pair, 0);
    result &= pair == (BG_PAIR);
    if (!result) {
        printw("PAIR TEST FAILD\n");
        return;
    }
    printw("PAIR TEST SUCCESS\n");
}

int main(void) {
    initscr();
    start_color();
    noecho();
    curs_set(0);
    cbreak();
    use_default_colors();
    init_extended_pair(PAIR, FG.color, BG.color);
    init_extended_pair(FG_PAIR, FG.color, -1);
    init_extended_pair(BG_PAIR, -1, BG.color);
    test_fg();
    test_bg();
    test_pair();

    getch();
    endwin();
}
