# Character Shape Generator 
A Java Swing-based GUI tool that generates ASCII-art shapes (squares, triangles, diamonds, pyramids) using simple text commands.

### Features ✨
* Draw with Any Character (e.g., `#`, `*`, `@`, `A`, `♥`)
* Supported shapes:
     * ▢ Square (e.g., X square 5)
     * ▲ Triangle (e.g., + triangle 4)
     * ◆ Diamond (e.g., $ diamond 7)
     * ⛫ Pyramid (e.g., % pyramid 3)
* Adjustable Size (1–20 rows, default=5)
* Help menu available

### How to Use 🚀
1. Command Format: Enter commands in the format: `<character> <shape> [size]`
   * `<character>`: The character to use for drawing the shape
   * `<shape>`: One of "square", "triangle", "diamond", or "pyramid"
   * `[size]`: Optional size parameter (1-20, default is 5)
     
2. Examples:
    * `# square 4` - Creates a 4x4 square using # characters
    * `* triangle 6` - Creates a right triangle with 6 rows using * characters
    * `@ pyramid` - Creates a pyramid with default size 5 using @ characters
    * `X diamond 7` - Creates a diamond with 7 rows in the top half using X characters

### Additionaly ⚙️
You can add more characters other than the defaoult ones I've used here.  
Just add a new method and add it to the Switch.


