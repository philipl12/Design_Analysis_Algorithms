#include <iostream>
#include <fstream>
using namespace std;


class TNode {
public:

    string chStr, code;
    int prob;
    TNode *next = NULL, *left = NULL, *right = NULL;

    TNode() {
        chStr = "dummy";
        prob = 0;
    }

    TNode(string chStrCopy, int probCopy) {
        chStr = chStrCopy;
        prob = probCopy;
    }

};

class LList {
public:

    TNode *listHead;

    LList() {
        listHead = new TNode();
    }

    void insertOneNode(TNode *listHeadCopy, TNode *newNode) {
        TNode *spot = findSpot(listHeadCopy, newNode);
        newNode->next = spot->next;
        spot->next = newNode;

    }

    TNode* findSpot(TNode *listHeadCopy, TNode *newNode) {
        TNode *spot = listHeadCopy;
        while (spot->next != NULL && spot->next->prob < newNode->prob) {
            spot = spot->next;
        }
        return spot;
    }

    void printList(TNode *listHeadCopy, ofstream &fileOut) {
        TNode *spot = listHeadCopy;
        do {
            fileOut << spot->chStr << ", " << spot->prob << ", " << spot->next->chStr << " --> ";
            spot = spot->next;
            if(spot->next == NULL) {
                fileOut << spot->chStr << ", " << spot->prob << ", NULL"  << " --> NULL\n";
            }
        } while(spot->next != NULL);

        fileOut << "\n\n";
    }

};

class HuffBinTree {
public:
    TNode *root;

    HuffBinTree() {
        root = new TNode();
    }

    void constructHuffmanLList(ifstream &fileIn, ofstream &fileOut) {
        string chStr;
        int prob;
        LList dummy = LList();

        while (fileIn >> chStr >> prob) {
            TNode *newNode = new TNode();
            newNode->chStr = chStr;
            newNode->prob = prob;
            dummy.insertOneNode(dummy.listHead, newNode);
            dummy.printList(dummy.listHead, fileOut);
        }
        fileIn.close();
    }

    void constructHuffmanBinTree(LList dummy, ofstream &fileOut) {
        TNode *spot = dummy.listHead->next;
        TNode *newNode = new TNode();

        while(spot->next != NULL) {
            newNode->chStr = spot->chStr + spot->next->chStr;
            newNode->prob = spot->prob + spot->next->prob;
            newNode->left = spot;
            newNode->right = spot->next;
            dummy.insertOneNode(spot, newNode);
            dummy.printList(dummy.listHead, fileOut);
            spot = spot->next->next;
        }

        cout << newNode->chStr << " line 100 " << newNode->prob << endl;
    }
};

int main(int argc, char** argv) {
    ifstream fileIn, fileIn2;
    ofstream fileOut1, fileOut2, fileOut3, fileOut4, fileOut5;
    string chStr;
    int prob;
    LList dummy = LList();
    fileIn.open(argv[1]);
    fileOut1.open(argv[2]);
    fileOut2.open(argv[3]);
    fileOut3.open(argv[4]);
    fileOut4.open(argv[5]);
    fileOut5.open(argv[6]);

    while (fileIn >> chStr >> prob) {
        TNode *newNode = new TNode();
        newNode->chStr = chStr;
        newNode->prob = prob;
        fileOut1 << newNode->chStr << "  #" << newNode->prob << endl;
        dummy.insertOneNode(dummy.listHead, newNode);
    }

    fileIn.close();
    fileIn2.open(argv[1]);
    HuffBinTree test = HuffBinTree();
    test.constructHuffmanLList(fileIn2, fileOut5);
    test.constructHuffmanBinTree(dummy, fileOut5);

    fileOut1.close();
    fileOut2.close();
    fileOut3.close();
    fileOut4.close();
    fileOut5.close();
    return 0;
}
