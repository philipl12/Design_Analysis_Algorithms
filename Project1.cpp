#include <iostream>
#include <fstream>

using namespace std;

int main(int argc, char** argv) {
    int letters[256];
    ifstream fileIn;
    ofstream fileOut;
    char myChar;

    for (int i = 0; i < 256; i++) {
        letters[i] = 0;
    }

    fileIn.open(argv[1]);
    fileOut.open(argv[2]);

    while (!fileIn.eof()) {
        fileIn.get(myChar);

        for (int i = 0; i < 256; i++) {
            if ((int)myChar == i) {
                letters[i]++;
                break;
            }
        }

    }

    for (int i = 33; i < 256; i++) {
        if (letters[i] > 0) {
            fileOut << (char)i << "   #" << letters[i] << endl;
        }
    }
    
    fileIn.close();
    fileOut.close();
    return 0;
}
