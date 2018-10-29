#include <iostream>
#include <fstream>
using namespace std;
const int tableSize = 10;

class LNode {
public:

    int data;
    LNode *next;

    LNode(int value) {
        data = value;
        next = NULL;
    }

};

class LListStack {
public:
    LNode *top;

    LListStack() {
        top = new LNode(0);
    }

    void push(LNode *actualTop, LNode *newNode) {
        LNode *spot = actualTop;
        newNode->next = spot->next;
        spot->next = newNode;
    }

    LNode* pop() {
        if(isEmpty(top)) {
            cout << "Stack is empty\n";
        }
        LNode *newPop = new LNode(0);
        newPop->data = top->next->data;
        top->next = top->next->next;
        top = top->next;
        return newPop;
    }

    int isEmpty(LNode *stack) {
        if (stack->next == NULL) return 1;
        else return 0;
    }

    void printStack(LNode *actualTop, ofstream &outFile) {
        LNode *spot = actualTop;
        outFile << "Top -> ";
        while(spot->next != NULL && spot->next->next != NULL) {
            outFile << "(" << spot->next->data << ", " << spot->next->next->data << ") -> ";
            spot = spot->next;
        }
        spot = spot->next;
        if (spot->next == NULL) {
            outFile << "(" << spot->data << ", NULL" << ") -> NULL\n\n";
        }
    }



};

class LListQueue {
public:
    LNode *head, *tail;

    LListQueue() {
        LNode *dummy = new LNode(0);
        head = tail = dummy;
    }

    void addTail(LListQueue someQueue, LNode *newNode) {
        if (someQueue.tail->data != 0) {
            someQueue.tail->next = newNode;
            someQueue.tail = newNode;
        }
        else { someQueue.head = newNode; }
        someQueue.tail = newNode;
    }

    LNode* deleteFront(LListQueue someQueue) {
        if (isEmpty(someQueue)) { cout << "Queue is empty\n"; }
        LNode *first = someQueue.head;
        someQueue.head = someQueue.head->next;
        if (someQueue.head == NULL) someQueue.tail = someQueue.head;
        return first;
    }

    int isEmpty(LListQueue someQueue) {
        if (someQueue.head->data == 0) return 1;
        return 0;
    }

    void printQueue() {

    }
};

class RadixSort {
public:
    int tableSize2 = tableSize;
    LListQueue hashTable[2][tableSize];
    int data, currentTable, previousTable, maxDigits, currentDigit;

    void loadStack(LNode *actualTop, ifstream &inFile, ofstream &outFile) {
        int largestNum = 0, data;
        LListStack test = LListStack();
        while (!inFile.eof()) {
            inFile >> data;
            if (data > largestNum) largestNum = data;
            LNode *newNode = new LNode(data);
            test.push(actualTop, newNode);
        }
        test.printStack(actualTop, outFile);
    }
};

int main(int argc, char** argv) {
    ifstream inFile;
    ofstream outFile1, outFile2;
    //create correct radix sort object

    inFile.open(argv[1]);
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);

    //top.loadStack(top.top, inFile, outFile2);

    inFile.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
