#!/usr/bin/python3
import board
import neopixel

# NeoPixels must be connected to D10, D12, D18 or D21 to work.
# Global configuration
LED_DATA_PIN = board.D10
LED_DATA_PIN2 = board.D18
COUNT_LED = 300
COUNT_LED2 = COUNT_LED
RGB = neopixel.GRB
BRIGHTNESS = 0.4
PIXELS = neopixel.NeoPixel(LED_DATA_PIN, COUNT_LED, pixel_order=RGB, brightness=BRIGHTNESS, auto_write=False)
PIXELS_2 = neopixel.NeoPixel(LED_DATA_PIN2, COUNT_LED2, pixel_order=RGB, brightness=BRIGHTNESS, auto_write=False)


PIXELS.fill((0, 0, 0))
PIXELS_2.fill((0, 0, 0))

PIXELS.show()
PIXELS_2.show()

exit(0)
