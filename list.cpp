#include <iostream>
#include <fstream>
using namespace std;

class LNode {
public:

    string chStr;
    int prob;
    LNode* next, head, tail;

    LNode() {
        chStr = "dummy";
        prob = 0;
        next = head = tail = NULL;
    }

    LNode(string chStrCopy, int probCopy) {
        chStr = chStrCopy;
        prob = probCopy;
    }

};

class LList {
public:

    LNode listHead2;

    LList() {
        listHead2 = LNode();
    }

};

int main(int argc, char** argv) {
    ifstream fileIn;
    ofstream fileOut;
    string chStr;
    int prob;
    LList listHead1 = LList();

    fileIn.open(argv[1]);
    fileOut.open(argv[2]);

    while (fileIn >> chStr >> prob) {
        LNode newNode = LNode(chStr, prob);

        listHead1.insertOneNode(listHead1.listHead2, newNode);

    }

    fileIn.close();
    fileOut.close();
    return 0;
}
