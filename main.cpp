#include <iostream>
#include <fstream>
#include <bits/stdc++.h>
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
    int numNodes, startID, whichHFunction, *childAry, **costMatrix;
    AStarNode *OpenList;
    AStarNode *CloseList;

    AStarSearch() {
        OpenList = new AStarNode(0, 0, 0);
        CloseList = new AStarNode(-1, -1, -1);
    }

    void initializeStuff() {
        cout << "Enter start ID: ";
        cin >> startID;
        while (startID <= 0 || startID > numNodes) {
            cout << "Starting ID is not within range, try again: ";
            cin >> startID;
        }

        cout << "Enter H Function: ";
        cin >> whichHFunction;
        while (whichHFunction <= 0 || whichHFunction > 3) {
            cout << "Not within range of 1 - 3, try again: ";
            cin >> whichHFunction;
        }

        childAry = new int[numNodes]();
        costMatrix = new int*[numNodes];
        for (int i = 0; i < numNodes; i++) {
            costMatrix[i] = new int[numNodes]();
        }
/*
        for (int i = 0; i < numNodes; i++) {
            for (int j = 0; j < numNodes; j++) {
                cout << costMatrix[i][j];
            }
            cout << endl;
        }
*/
    }

    void loadMatrix() {

    }

    void copyChildList(int index) {

    }
};

int main(int argc, char const *argv[]) {
    ifstream input1;
    ofstream outFile1, outFile2;
    int input2 = 0, input3 = 0;
    AStarSearch findMe = AStarSearch();

    input1.open(argv[1]);
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);

    if (input1.good()) {
        string header;
        getline(input1, header);
        istringstream hCopy(header);
        hCopy >> findMe.numNodes;
    }
    findMe.initializeStuff();

    input1.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
