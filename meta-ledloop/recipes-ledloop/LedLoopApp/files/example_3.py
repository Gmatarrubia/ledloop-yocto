#!/usr/bin/python3

# Simple test for NeoPixels on Raspberry Pi
import time
import board
import neopixel


# Global configuration
LED_DATA_PIN = board.D10
LED_DATA_PIN2 = board.D18
COUNT_LED = 24
COUNT_LED2 = 24
RGB = neopixel.GRB
BRIGHTNESS = 0.4
PIXELS = neopixel.NeoPixel(LED_DATA_PIN, COUNT_LED, pixel_order=RGB, brightness=BRIGHTNESS, auto_write=False)
PIXELS_2 = neopixel.NeoPixel(LED_DATA_PIN2, COUNT_LED2, pixel_order=RGB, brightness=BRIGHTNESS, auto_write=False)

PIXELS.fill((0, 0, 0))
PIXELS_2.fill((0, 0, 0))

while True:
    PIXELS.fill((0, 0, 0))
    PIXELS_2.fill((0, 0, 0))
    for p in range(0, COUNT_LED):
        PIXELS.fill((0, 0, 80))
        PIXELS_2.fill((0, 0, 80))
        PIXELS[p]=(250, 250, 250)
        PIXELS_2[p]=(250, 250, 250)
        PIXELS.show()
        PIXELS_2.show()
        time.sleep(0.01)
