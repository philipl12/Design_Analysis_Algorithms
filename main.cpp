#include <iostream>
#include <fstream>
using namespace std;

class AStarNode {
public:
    int ID, numEdgeFromStart = 0, gStar, hStar, fStar;
    AStarNode *next = NULL, *parent = NULL;

    AStarNode(int ID, int gStar, int hStar) {
        this->ID = ID;
        this->gStar = gStar;
        this->hStar = hStar;
        fStar = gStar + hStar;
    }
};

class AStarSearch {
public:
    int numNodes, start, whichHFunction;
    int costMatrix;
};

int main(int argc, char const *argv[]) {
    ifstream input1;
    ofstream outFile1, outFile2;
    int input2 = 0, input3 = 0;

    input1.open(argv[1]);
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);



    input1.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
