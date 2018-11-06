#include <iostream>
#include <fstream>
#include <bits/stdc++.h>
#include <cmath>
using namespace std;

class xyCoord;

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
    Point *pointSet;
    int **imgAry;
    xyCoord *KCentroids;

    KMean(int nRows, int nCols, int minV, int maxV, int K) {
        imgAry = new int*[nRows];
        for (int i = 0; i < nRows; i++) imgAry[i] = new int[nCols];

        KCentroids = new xyCoord[K + 1];
        numRows = nRows;
        numCols = nCols;
        minVal = minV;
        maxVal = maxV;
        this->K = K;
    }

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

    void loadPointSet(ifstream &outFile) {
        for (int i = 0; i < numPts; i++) {
            outFile >> pointSet[i].xCoord >> pointSet[i].yCoord;
            pointSet[i].distance = 9999.0;
        }
    }

    void assignLabel() {
        int KCount = 1;
        for (int i = 0; i < numPts; i++) {
            pointSet[i].label = KCount;
            KCount++;
            if (KCount > K) KCount = 1;
        }
    }

    void point2Image() {
        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                imgAry[i][j] = 0;
            }
        }
        for (int i = 0; i < numPts; i++) {
            int j = pointSet[i].xCoord;
            int k = pointSet[i].yCoord;
            imgAry[j][k] = pointSet[i].label;
        }
    }

    void printImage(ofstream &outFile) {

        for (int i = 0; i < numRows; i++) {
            for (int j = 0; j < numCols; j++) {
                if (imgAry[i][j] > 0) outFile << imgAry[i][j];
                else { outFile << ' '; }
            }
            outFile << endl;
        }
    }

    void computeCentroids() {
        int *sumX = new int[K];
        int *sumY = new int[K];
        int *totalPt = new int[K];
        int index = 0, label;

        for (int i = 0; i < K; i++) {
            sumX[i] = 0;
            sumY[i] = 0;
            totalPt[i] = 0;
        }

        while (index < numPts) {
            label = pointSet[index].label;
            sumX[label] += pointSet[index].xCoord;
            sumY[label] += pointSet[index].yCoord;
            totalPt[label]++;
            index++;
        }
        /*
        for (int index = 0; index < numPts; index++) {
            label = pointSet[index].label;
            sumX[label] += pointSet[index].xCoord;
            sumY[label] += pointSet[index].yCoord;
            totalPt[label]++;
        }
        */
        label = 1;
        while (label <= K) {
            KCentroids[label].xCoord = (sumX[label]/ totalPt[label]);
            KCentroids[label].yCoord = (sumX[label]/ totalPt[label]);
            label++;
        }
        /*
        for (label = 1; label <= K; label++) {
            KCentroids[label].xCoord = (sumX[label]/ totalPt[label]);
            KCentroids[label].yCoord = (sumX[label]/ totalPt[label]);
        }
        */
    }

    double computeDist(Point p1, Point p2) {
        int xSquared = (p2.xCoord - p1.xCoord) * (p2.xCoord - p1.xCoord);
        int ySquared = (p2.yCoord - p1.yCoord) * (p2.yCoord - p1.yCoord);
        double distance = sqrt(xSquared + ySquared);
        return distance;
    }

    void kMeansClustering() {
        int iteration = 0;
        assignLabel();

    }
};

int main(int argc, char** argv) {
    ifstream inFile;
    ofstream outFile1, outFile2, outFile3;
    int numRows, numCols, minVal, maxVal, K;


    inFile.open(argv[1]);

    if (inFile.good()) {
        string header;
        getline(inFile, header);
        istringstream hCopy(header);
        hCopy >> skipws >> numRows >> numCols >> minVal >> maxVal;
    }

    cout << "Entere a value K:";
    cin >> K;
    KMean test = KMean(numRows, numCols, minVal, maxVal, K);

    outFile1.open(argv[2]);
    outFile2.open(argv[3]);
    outFile3.open(argv[4]);

    test.numPts = test.extractPts(inFile, outFile1);

    outFile1.close();
    ifstream outFile_1;
    outFile_1.open(argv[2]);
    test.loadPointSet(outFile_1);
    //test.kMeansClustering();

    for (int i = 0; i < test.numRows; i++) delete[] test.imgAry[i];
    delete[] test.imgAry;

    inFile.close();
    outFile_1.close();
    outFile2.close();
    outFile3.close();
    return 0;
}
