#include <iostream>
#include <fstream>
using namespace std;

class KMean {
public:
    class Point {
        int xCoord, yCoord, label;
        double distance;
    };

    class xyCoord {
        int xCoord, yCoord, label;
    };

    int K, numPts = 0, numRows, numCols, minVal, maxVal, change = 0;
    Point * pointSet; //[numPts]
    int * imgAry; //[numRows][numCols]
    xyCoord * KCentroids; //[K]

    KMean() {}

    int extractPts(ifstream &inFile, ofstream &outFile) {
        numPts = 0;
        int i, j;
        if (inFile.good()) {
            string sLine;
            getline(inFile, sLine);
            cout << sLine << endl;
        }
        return numPts;
    }

    void loadPointSet() {}
};

int main(int argc, char** argv) {
    ifstream inFile;
    ofstream outFile1, outFile2, outFile3;
    KMean test = KMean();

    inFile.open(argv[1]);
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);
    outFile3.open(argv[4]);

    test.extractPts(inFile, outFile1);


    inFile.close();
    outFile1.close();
    outFile2.close();
    outFile3.close();
    return 0;
}
