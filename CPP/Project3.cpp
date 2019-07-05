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

        while(spot->next != NULL) {
            TNode *newNode = new TNode();
            newNode->chStr = spot->chStr + spot->next->chStr;
            newNode->prob = spot->prob + spot->next->prob;
            newNode->left = spot;
            newNode->right = spot->next;
            dummy.insertOneNode(spot, newNode);
            dummy.printList(dummy.listHead, fileOut);
            spot = spot->next->next;
            root = newNode;
        }
    }

    void getCode(TNode *T, string code, ofstream &fileOut) {
        if (isLeaf(T)) {
            T->code = code;
            fileOut << T->chStr << ' ' << T->code << endl;
        }
        else {
            getCode(T->left, code + "0", fileOut);
            getCode(T->right, code + "1", fileOut);
        }
    }

    bool isLeaf(TNode *T) {
        if (T->left == NULL && T->right == NULL) {
            return true;
        }
        return false;
    }

    void preOrderTraversal(TNode *T, ofstream &fileOut) {
        if (T == NULL) { return; }
        else {
            printNode(T, fileOut);
            preOrderTraversal(T->left, fileOut);
            preOrderTraversal(T->right, fileOut);
        }
    }

    void inOrderTraversal(TNode *T, ofstream &fileOut) {
        if (T == NULL) { return; }
        else {
            inOrderTraversal(T->left, fileOut);
            printNode(T, fileOut);
            inOrderTraversal(T->right, fileOut);
        }
    }

    void postOrderTraversal(TNode *T, ofstream &fileOut) {
        if (T == NULL) { return; }
        else {
            postOrderTraversal(T->left, fileOut);
            postOrderTraversal(T->right, fileOut);
            printNode(T, fileOut);
        }
    }

    void printNode(TNode *T, ofstream &fileOut) {
        fileOut << T->chStr << ' ' << T->prob << endl;
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
    fileOut5 << '\n';
    test.constructHuffmanBinTree(dummy, fileOut5);
    fileOut5 << '\n';
    test.getCode(test.root, "", fileOut5);
    test.preOrderTraversal(test.root, fileOut2);
    test.inOrderTraversal(test.root, fileOut3);
    test.postOrderTraversal(test.root, fileOut4);

    fileOut1.close();
    fileOut2.close();
    fileOut3.close();
    fileOut4.close();
    fileOut5.close();
    return 0;
}
