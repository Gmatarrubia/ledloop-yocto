import board
import neopixel
import time

print(board.D16)
pixels = neopixel.NeoPixel(board.D10, 80, pixel_order=neopixel.RGBW)

for pix in pixels:
        pix = (10, 0, 0)
        pixels.show()
        time.sleep(1)

