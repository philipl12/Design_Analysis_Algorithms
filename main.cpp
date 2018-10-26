#include <iostream>
#include <fstream>
using namespace std;

class LNode {
public:

    int data;
    LNode *next;

    LNode(int data) {
        this->data = data;
        next = NULL;
    }
};

class LListStack {
public:

    LNode *top;

    LListStack() {
        top = NULL;
    }

    void push(LNode *newTop, LNode *newNode) {
        newNode->next = newTop->next;
        newTop->next = newNode;
    }

    LNode* pop() {
        if(isEmpty(top)) {
            cout << "Stack is empty\n";
        }
        LNode *newPop = LNode(top->next->data);
        top->next = top->next->next;
        top = top->next;
        return newPop;
    }

    int isEmpty(LNode *stack) {
        if (stack->next == NULL) return 1;
        else return 0;
    }

    void printStack(ofstream &outFile) {
        LNode *spot = top;
        outFile << "Top -> ";
        while(spot->next != NULL && spot->next->next != NULL) {
            outFile << "(" << spot->data << ", " << spot->next->data << ") -> ";
            spot = spot->next;
        }
        if (spot->next != NULL && spot->next->next == NULL) {
            outFile << "(" << spot->data << ", " << ") -> NULL\n\n";
        }
        else outFile << "NULL\n\n";
    }
};

void loadStack(ifstream &inFile, ofstream &outFile) {
    LListStack newTop = LListStack();
    int largestNum = 0, data;
    while (!inFile.eof()) {
        inFile.get(data);
        if (data > largestNum) largestNum = data;
        LNode newNode = LNode(data);
        newTop.push(newTop->top, newNode);
    }
    newTop.printStack(outFile);
}

int main(int argc, char** argv) {
    ifstream inFile;
    ofstream outFile1, outFile2;

    inFile.open(argv[1]);
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);

    loadStack(inFile, outFile2);

    inFile.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
