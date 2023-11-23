# SPDX-FileCopyrightText: 2021 ladyada for Adafruit Industries

# SPDX-License-Identifier: MIT

# Simple test for NeoPixels on Raspberry Pi
import time
import board
import neopixel


# Global configuration
LED_DATA_PIN = board.D10
LED_DATA_PIN2 = board.D18
COUNT_LED = 18
COUNT_LED2 = 24
RGB = neopixel.GRB
BRIGHTNESS = 0.4
PIXELS = neopixel.NeoPixel(LED_DATA_PIN, COUNT_LED, pixel_order=RGB, brightness=BRIGHTNESS, auto_write=False)
PIXELS_2 = neopixel.NeoPixel(LED_DATA_PIN2, COUNT_LED2, pixel_order=RGB, brightness=BRIGHTNESS, auto_write=False)

def wheel(pos):
    # Input a value 0 to 255 to get a color value.
    # The colours are a transition r - g - b - back to r.
    if pos < 0 or pos > 255:
        r = g = b = 0
    elif pos < 85:
        r = int(pos * 3)
        g = int(255 - pos * 3)
        b = 0
    elif pos < 170:
        pos -= 85
        r = int(255 - pos * 3)
        g = 0
        b = int(pos * 3)
    else:
        pos -= 170
        r = 0
        g = int(pos * 3)
        b = int(255 - pos * 3)
    return (r, g, b) if RGB in (neopixel.RGB, neopixel.GRB) else (r, g, b, 0)


def rainbow_cycle(wait):
    for j in range(255):
        for i in range(COUNT_LED2):
            pixel_index = (i * 256 // COUNT_LED2) + j
            PIXELS[i] = wheel(pixel_index & 255)
            PIXELS_2[i] = wheel(pixel_index & 255)
        PIXELS.show()
        PIXELS_2.show()
        time.sleep(wait)


while True:
    # Comment this line out if you have RGBW/GRBW NeoPixels
    PIXELS.fill((255, 0, 0))
    PIXELS_2.fill((255, 0, 0))
    # Uncomment this line if you have RGBW/GRBW NeoPixels
    # PIXELS.fill((255, 0, 0, 0))
    PIXELS.show()
    PIXELS_2.show()
    time.sleep(1)

    # Comment this line out if you have RGBW/GRBW NeoPixels
    PIXELS.fill((0, 255, 0))
    PIXELS_2.fill((0, 255, 0))
    # Uncomment this line if you have RGBW/GRBW NeoPixels
    # PIXELS.fill((0, 255, 0, 0))
    PIXELS.show()
    PIXELS_2.show()
    time.sleep(1)

    # Comment this line out if you have RGBW/GRBW NeoPixels
    PIXELS.fill((0, 0, 255))
    PIXELS_2.fill((0, 0, 255))
    # Uncomment this line if you have RGBW/GRBW NeoPixels
    # PIXELS.fill((0, 0, 255, 0))
    PIXELS.show()
    PIXELS_2.show()
    time.sleep(1)

    rainbow_cycle(0.001)  # rainbow cycle with 1ms delay per step
