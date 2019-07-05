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
//        CloseList = new AStarNode(-1, -1, -1);
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

        childAry = new int[numNodes + 1]();
        costMatrix = new int*[numNodes + 1];
        for (int i = 0; i < numNodes + 1; i++) {
            costMatrix[i] = new int[numNodes + 1]();
        }
/*
        for (int i = 0; i < numNodes + 1; i++) {
            for (int j = 0; j < numNodes + 1; j++) {
                cout << costMatrix[i][j];
            }
            cout << endl;
        }
*/
    }
    void printOpen(ofstream &outFile2) {
        AStarNode *spot = OpenList;

        outFile2 << "*** OPEN List ***\n";
        do {
            outFile2 << spot->ID << ", " << spot->fStar << ", " << spot->next->ID << " --> ";
            spot = spot->next;
            if(spot->next == NULL) {
                outFile2 << spot->ID << ", " << spot->fStar << ", NULL"  << " --> NULL\n";
            }
        } while(spot->next != NULL);
    }

    void loadMatrix(ifstream &input1) {
        int row = 0, col = 0, cost = 0;
        while(input1 >> row >> col >> cost) {
            costMatrix[row][col] = cost;
            costMatrix[col][row] = cost;
        }
    }

    bool checkPath(AStarNode *currentNode) {
        return false;
    }

    void copyChildList(int matrixIndex) {
        for (int i = 0; i < numNodes + 1; i++) {
            childAry[i] = costMatrix[matrixIndex][i];
        }
/*
        for (int i = 0; i < numNodes + 1; i++) {
            cout << childAry[i] << ' ';
        }
        cout << endl;
*/
    }

    void OpenInsert(AStarNode *SNode) {
        AStarNode *spot = OpenList;
        while (spot->next != NULL && spot->fStar < SNode->fStar) {
            spot = spot->next;
        }
        SNode->next = spot->next;
        spot->next = SNode;
    }

    AStarNode* OpenRemove() {
        AStarNode *copy = OpenList->next;
        OpenList->next = copy->next;
        copy->next = NULL;
        return copy;
    }

    bool IsInCloseList(AStarNode *childNode, AStarNode *oldNode) {
        AStarNode *spot = CloseList;
        do {
            if (childNode->ID == spot->next->ID) {
                oldNode = spot;
                return true;
            }
            spot = spot->next;
        } while (spot->next != NULL);

        return false;
    }

    void removeClose(AStarNode *oldNode) {
        
    }

    void tracePath(AStarNode *currentNode, ofstream &outFile1) {

    }
};

int main(int argc, char const *argv[]) {
    ifstream input1;
    ofstream outFile1, outFile2;
    int input2 = 0, input3 = 0, matrixIndex = 0, numEdgeFromStart = 0;
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
    findMe.loadMatrix(input1);

    AStarNode *SNode = new AStarNode(findMe.startID, 0, 0);
    findMe.OpenInsert(SNode);
    findMe.printOpen(outFile2);
    AStarNode *currentNode = new AStarNode(0, 0, 0);
    while (!findMe.checkPath(currentNode)) {
        currentNode = findMe.OpenRemove();
        matrixIndex = currentNode->ID;
        findMe.copyChildList(matrixIndex);

        for (int childIndex = 1; childIndex < findMe.numNodes + 1; childIndex++) {
            if (findMe.childAry[childIndex] > 0) {
                AStarNode *childNode = new AStarNode(childIndex, findMe.childAry[childIndex], 0);
                AStarNode *oldNode = new AStarNode(0, 0, 0);
                if(!findMe.IsInCloseList(childNode, oldNode)) {
                    AStarNode *parent = currentNode;
                    numEdgeFromStart++;
                    findMe.OpenInsert(childNode);
                    findMe.printOpen(outFile2);
                }
                else {
                    if (childNode->fStar < oldNode->fStar) {
                        findMe.removeClose(oldNode);
                        AStarNode *parent = currentNode;
                        findMe.OpenInsert(childNode);
                        findMe.printOpen(outFile2);
                    }
                }
            }
        }
    }


    findMe.tracePath(currentNode, outFile1);


    input1.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
