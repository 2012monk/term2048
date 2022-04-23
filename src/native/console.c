#include "console.h"
#include "termui_console_Console.h"
#include <ncurses.h>

#define MAX_COLOR COLORS;
#define COLOR_MODE;
static int inAction = 0;

void endDraw() {
  if (inAction > 0) inAction--;
  if (!inAction && !isendwin()) {
    move(0, 0);
    refresh();
  }
}

void startDraw(void) {
  inAction++;
}

JNIEXPORT void JNICALL Java_termui_console_Console_endDraw (JNIEnv *env, jclass clazz) {
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_startDraw (JNIEnv *env, jclass clazz) {
  startDraw();
}

void drawHorizontalLine(int x, int y, int width, int height) {
  mvhline(x, y, ACS_HLINE, width);
  mvhline(x + height, y, ACS_HLINE, width);
}

void drawVerticalLine(int x, int y, int width, int height) {
  mvvline(x, y, ACS_VLINE, height);
  mvvline(x, y + width, ACS_VLINE, height);
}

void drawCorners(int x, int y, int width, int height) {
  mvaddch(x, y, ACS_ULCORNER);
  mvaddch(x + height, y, ACS_LLCORNER);
  mvaddch(x, y + width, ACS_URCORNER);
  mvaddch(x + height, y + width, ACS_LRCORNER);
}

JNIEXPORT jint JNICALL Java_termui_console_Console_readBytes (JNIEnv *env, jclass clazz) {
  return wgetch(stdscr);
}
JNIEXPORT jint JNICALL Java_termui_console_Console_blockingReadBytes (JNIEnv *env, jclass clazz) {
  timeout(-1);
  int c = wgetch(stdscr);
  nodelay(stdscr, TRUE);
  return c;
}


JNIEXPORT jint JNICALL Java_termui_console_Console_getScreenWidth (JNIEnv *env, jobject obj) {
  return COLS;
}

JNIEXPORT jint JNICALL Java_termui_console_Console_getScreenHeight (JNIEnv *env, jobject obj) {
  return LINES;
}

JNIEXPORT void JNICALL Java_termui_console_Console_shutdown (JNIEnv *env, jobject obj) {
  endwin();
}

JNIEXPORT void JNICALL Java_termui_console_Console_clearLine (JNIEnv *env, jobject obj, jint x) {
  move(x, 0);
  clrtoeol();
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_init (JNIEnv *env, jobject obj) {
  initscr();
  curs_set(0);
  keypad(stdscr, TRUE);
  nodelay(stdscr, TRUE);
  cbreak();
  noecho();
  color_on();
  attr_on(A_BOLD, 0);
  erase();
  refresh();
}

JNIEXPORT void JNICALL Java_termui_console_Console_refresh (JNIEnv *env, jclass clazz) {
  refresh();
}

JNIEXPORT void JNICALL Java_termui_console_Console_drawBorder (JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jint fg, jint bg) {
  startDraw();
  int n = set_color_attr(fg, bg);
  width--;
  height--;
  drawHorizontalLine(x, y, width, height);
  drawVerticalLine(x, y, width, height);
  drawCorners(x, y, width, height);
  unset_color_attr(n);
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_drawBorderMinMax (JNIEnv *env, jclass clazz, jint x0, jint y0, jint x1, jint y1, jint fg, jint bg) {
  startDraw();
  int n = set_color_attr(fg, bg);
  int w = y1 - y0 - 1;
  int h = x1 - x0 - 1;
  drawVerticalLine(x0,y0, w, h);
  drawHorizontalLine(x0, y0, w, h);
  drawCorners(x0, y0, w, h);
  unset_color_attr(n);
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_drawChar (JNIEnv *env, jclass clazz, jint x, jint y, jchar chr, jint fg, jint bg) {
  startDraw();
  int n = set_color_attr(fg, bg);
  mvaddch(x, y, chr);
  unset_color_attr(n);
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_drawString (JNIEnv *env, jclass clazz, jint x, jint y, jstring str, jint fg, jint bg) {
  startDraw();
  const char *cstr = (*env)->GetStringUTFChars(env, str, NULL);
  int n = set_color_attr(fg, bg);
  mvaddstr(x, y, cstr);
  (*env)->ReleaseStringUTFChars(env, str, cstr);
  unset_color_attr(n);
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_clearArea (JNIEnv *env, jclass clazz, jint x, jint y, jint width, jint height, jchar chr, jint fg, jint bg) {
  startDraw();
  int n = set_color_attr(fg, bg);
  // start inclusive, end exclusive
  for (int i = x; i < x + height; i++) {
    for (int j = y; j < y + width ; j++) {
      mvaddch(i, j, chr);
    }
  }
  unset_color_attr(n);
  endDraw();
}

JNIEXPORT void JNICALL Java_termui_console_Console_clearScreen (JNIEnv *env, jclass clazz) {
  erase();
  endDraw();
}
