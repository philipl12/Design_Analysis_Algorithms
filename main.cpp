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

    void constructHuffmanLList(int charCounts[256], LList dummy, ofstream &outFile) {
        string chStr;
        int prob;

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

    void constructHuffmanBinTree(LList dummy, ofstream &fileOut) {
        TNode *spot = dummy.listHead->next;

        while(spot->next != NULL) {
            TNode *newNode = new TNode();
            newNode->chStr = spot->chStr + spot->next->chStr;
            newNode->prob = spot->prob + spot->next->prob;
            newNode->left = spot;
            newNode->right = spot->next;
            dummy.insertOneNode(spot, newNode);
            spot = spot->next->next;
            root = newNode;
        }
        dummy.printList(dummy.listHead, fileOut);
    }

    void getCode(TNode *T, string code, string charCode[256]) {
        int index;
        char tempChar[1];
        tempChar[0] = T->chStr[0];
        index = (int)tempChar[0];


        if (isLeaf(T)) {
            T->code = code;
            charCode[index] = code;
        }
        else {
            getCode(T->left, code + "0", charCode);
            getCode(T->right, code + "1", charCode);
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

    void decode(ifstream &inFile, ofstream &outFile, TNode *T) {
        char nextBit;

        if (isLeaf(T)) {
            outFile << T->chStr;
            decode(inFile, outFile, root);
        }
        else if (inFile.eof()) {
            cout << "The encode file is corrupted\n";
            exit(1);
        }
        else {
            inFile.get(nextBit);
            if(nextBit == '0') {
                decode(inFile, outFile, T->left);
            }
            else if (nextBit == '1') {
                decode(inFile, outFile, T->right);
            }
        }
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

void encode(ifstream &inFile, ofstream &outFile, string charCode[256]) {
    char charIn;
    int index = 0, count = 1;
    string code;

    while (!inFile.eof()) {
        inFile.get(charIn);
        cout << charIn << endl;
        index = (int)charIn;
        code = charCode[index];
        outFile << code;
        cout << "encode pass " << count++ << endl;

    }
}


int main(int argc, char** argv) {
    int charCounts[256];
    string charCode[256];
    string fileName;
    LList dummy = LList();
    HuffBinTree test = HuffBinTree();

    ifstream inFile1, inFile2, inFile3;
    ofstream outFile1, outFile2, outFile3, outFile4;

    for (int i = 0; i < 256; i++) {
        charCounts[i] = 0;
        charCode[i] = "";
    }

    inFile1.open(argv[1]);
    inFile2.open(argv[2]);
    outFile1.open(argv[3]);
    outFile2.open(argv[4]);
    outFile3.open(argv[5]);
    outFile4.open(argv[6]);

    computeCount(inFile1, charCounts);
    printAry(outFile1, charCounts);
    test.constructHuffmanLList(charCounts, dummy, outFile4);
    outFile4 << "\n";
    test.constructHuffmanBinTree(dummy, outFile4);
    test.getCode(test.root, "", charCode);
    //encode(inFile2, outFile2, charCode);
    cout << "Enter a file to be decoded:";
    cin >> fileName;
    inFile3.open(fileName);
    test.decode(inFile3, outFile3, test.root);

    inFile1.close();
    inFile2.close();
    inFile3.close();
    outFile1.close();
    outFile2.close();
    outFile3.close();
    outFile4.close();
    return 0;
}
