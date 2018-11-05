#include <iostream>
#include <fstream>
#include <bits/stdc++.h>
using namespace std;

class KMean {
public:
    class Point {
    public:
        int xCoord, yCoord, label;
        double distance;
    };

    class xyCoord {
    public:
        int xCoord, yCoord, label;
    };

    int K, numPts = 0, numRows, numCols, minVal, maxVal, change = 0;
    Point* pointSet; //[numPts]
    int* imgAry = NULL; //[numRows][numCols]
    xyCoord* KCentroids; //[K]

    KMean() {}

    int extractPts(ifstream &inFile, ofstream &outFile) {
        int point;

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                inFile >> point;
                if (point > 0) {
                    numPts++;
                    outFile << i << ' ' << j << endl;
                }
            }
        }
        pointSet = new Point[numPts];
        return numPts;
    }

    void loadPointSet(ofstream &outFile, Point pointSet) {}
};

int main(int argc, char** argv) {
    ifstream inFile;
    ofstream outFile1, outFile2, outFile3;
    KMean test = KMean();

    inFile.open(argv[1]);

    if (inFile.good()) {
        string header;
        getline(inFile, header);
        istringstream hCopy(header);
        hCopy >> skipws >> test.numRows >> test.numCols >> test.minVal >>test.maxVal;
    }
    test.imgAry = new int[test.numCols][test.numRows];

    outFile1.open(argv[2]);
    outFile2.open(argv[3]);
    outFile3.open(argv[4]);

    test.extractPts(inFile, outFile1);

    outFile1.close();



    delete[] test.imgAry;

    inFile.close();
    outFile2.close();
    outFile3.close();
    return 0;
}
