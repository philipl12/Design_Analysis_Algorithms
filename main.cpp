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

    void printList(TNode *listHeadCopy, ofstream &outFile) {
        TNode *spot = listHeadCopy;
        do {
            outFile << spot->chStr << ", " << spot->prob << ", " << spot->next->chStr << " --> ";
            spot = spot->next;
            if(spot->next == NULL) {
                outFile << spot->chStr << ", " << spot->prob << ", NULL"  << " --> NULL\n";
            }
        } while(spot->next != NULL);
    }

};

class HuffBinTree {
public:
    TNode *root;

    HuffBinTree() {
        root = new TNode();
    }

    void constructHuffmanLList(int charCounts[256], ofstream &outFile) {
        string chStr;
        int prob;
        LList dummy = LList();

        for (int i = 0; i < 256; i++) {
            if (charCounts[i] > 0) {
                chStr = (char)i;
                prob = charCounts[i];
                TNode *newNode = new TNode();
                newNode->chStr = chStr;
                newNode->prob = prob;
                dummy.insertOneNode(dummy.listHead, newNode);
            }
        }
        dummy.printList(dummy.listHead, outFile);

    }

    void constructHuffmanBinTree(LList dummy, ofstream &outFile) {
        TNode *spot = dummy.listHead->next;

        while(spot->next != NULL) {
            TNode *newNode = new TNode();
            newNode->chStr = spot->chStr + spot->next->chStr;
            newNode->prob = spot->prob + spot->next->prob;
            newNode->left = spot;
            newNode->right = spot->next;
            dummy.insertOneNode(spot, newNode);
            dummy.printList(dummy.listHead, outFile);
            spot = spot->next->next;
            root = newNode;
        }
    }

    void getCode(TNode *T, string code, ofstream &outFile) {
        if (isLeaf(T)) {
            T->code = code;
            outFile << T->chStr << ' ' << T->code << endl;
        }
        else {
            getCode(T->left, code + "0", outFile);
            getCode(T->right, code + "1", outFile);
        }
    }

    bool isLeaf(TNode *T) {
        if (T->left == NULL && T->right == NULL) {
            return true;
        }
        return false;
    }

    void printNode(TNode *T, ofstream &outFile) {
        outFile << T->chStr << ' ' << T->prob << endl;
    }

};

void printAry(ofstream &outFile4, int charCounts[256]) {
    for (int i = 32; i < 256; i++) {
        if (charCounts[i] > 0) {
            outFile4 << (char)i << "   #" << charCounts[i] << endl;
        }
    }
}

void computeCount(ifstream &inFile1, int charCounts[256]) {
    char charIn;

    while (!inFile1.eof()) {
        inFile1.get(charIn);

        for (int i = 0; i < 256; i++) {
            if ((int)charIn == i) {
                charCounts[i]++;
                break;
            }
        }
    }

}

int main(int argc, char** argv) {
    int charCounts[256];
    char charCode[256];
    string fileName = "";

    ifstream inFile1, inFile2;
    ofstream outFile1, outFile2, outFile3, outFile4;

    for (int i = 0; i < 256; i++) {
        charCounts[i] = 0;
        charCode[i] = ' ';
    }

    inFile1.open(argv[1]);
    inFile2.open(argv[2]);
    outFile1.open(argv[3]);
    outFile2.open(argv[4]);
    outFile3.open(argv[5]);
    outFile4.open(argv[6]);

    computeCount(inFile1, charCounts);
    printAry(outFile4, charCounts);

    inFile1.close();
    inFile2.close();
    outFile1.close();
    outFile2.close();
    outFile3.close();
    outFile4.close();
    return 0;
}
