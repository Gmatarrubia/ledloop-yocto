# Simple test for NeoPixels on Raspberry Pi
import time
import board
import neopixel


# Choose an open pin connected to the Data In of the NeoPixel strip, i.e. board.D18
# NeoPixels must be connected to D10, D12, D18 or D21 to work.
pixel_pin = board.D10
pixel2_pin = board.D18

# The number of NeoPixels
num_pixels = 24

# The order of the pixel colors - RGB or GRB. Some NeoPixels have red and green reversed!
# For RGBW NeoPixels, simply change the ORDER to RGBW or GRBW.
ORDER = neopixel.GRB

pixels = neopixel.NeoPixel(
    pixel_pin, num_pixels, brightness=0.4, auto_write=False, pixel_order=ORDER
)

pixels2 = neopixel.NeoPixel(
    pixel2_pin, num_pixels, brightness=0.4, auto_write=False, pixel_order=ORDER
)

pixels.fill((0, 0, 0))
pixels2.fill((0, 0, 0))

while True:
    pixels.fill((0, 0, 0))
    pixels2.fill((0, 0, 0))
    for p in range(0, 200):
        pixels.fill((0, 0, 80))
        pixels2.fill((0, 0, 80))
        pixels[p]=(250, 250, 250)
        pixels2[p]=(250, 250, 250)
        pixels.show()
        pixels2.show()
        time.sleep(0.01)
