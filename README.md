# WAVE file parser
A java library that parses .wav files. To parse a file, call
```
WavParser.parseFile(<path to file>)
```
The program can easily be extended to handle other RIFF formats by adding your own chunk classes. Currently it should support all chunks needed to parse .wav files.

I wrote this library since I was unable to find another library that does the same thing, and as a learning experience. If you are trying to understand the wave file format I recommend checking out [this website](https://ccrma.stanford.edu/courses/422-winter-2014/projects/WaveFormat/), it helped me a great deal when writing this program.