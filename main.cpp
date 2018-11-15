#include <iostream>
#include <fstream>
#include <bits/stdc++.h>
using namespace std;

class Scheduling {
public:
    class Node {
    public:
        int jobID, jobTime;
        Node *next = NULL;

        Node() {
            jobID = 0;
            jobTime = 0;
        }
    };

    int numNodes, totalJobTimes;
    int **adjacencyMatrix, **scheduleTable;
    int *jobTimeAry, *processJob, *processTime, *parentCount, *kidCount, *jobDone, *jobMarked;
    Node *open;

    Scheduling() {
        open = new Node();
    }

    void loadMatrix(ifstream &inFile1) {
        int i = 0, j = 0;

        while (inFile1 >> i >> j) {
            adjacencyMatrix[i][j] = 1;
        }
    }

    int computTotalTimes(ifstream &inFile2) {
        int totalTimes = 0, job, time, garbage;
        jobTimeAry = new int[numNodes + 1];

        inFile2 >> garbage; //used to dispose of header
        while(inFile2 >> job >> time) {
            totalTimes += time;
            jobTimeAry[job] = time;
        }
        return totalTimes;
    }

    void initializeArrays() {
        processJob = new int[numNodes + 1];
        processTime = new int[numNodes + 1];
        parentCount = new int[numNodes + 1];
        kidCount = new int[numNodes + 1];
        jobDone = new int[numNodes + 1];
        jobMarked = new int[numNodes + 1];
        scheduleTable = new int*[numNodes + 1];
        for (int i = 0; i < numNodes + 1; i++) scheduleTable[i] = new int[totalJobTimes + 1];
        for (int i = 0; i < numNodes + 1; i++) {
            processJob[i] = 0;
            processTime[i] = 0;
            parentCount[i] = 0;
            kidCount[i] = 0;
            jobDone[i] = 0;
            jobMarked[i] = 0;
            for (int j = 0; j < totalJobTimes + 1; j++) {
                scheduleTable[i][j] = 0;
            }
        }
        for (int i = 1; i < numNodes + 1; i++) {
            for (int j = 1; j < numNodes + 1; j++) {
                parentCount[j] += adjacencyMatrix[i][j];
                kidCount[i] += adjacencyMatrix[i][j];
            }
        }
    }

    int getUnmarkedOrphan() {
        for (int i = 1; i < numNodes + 1; i++) {
            if (jobMarked[i] == 0 && parentCount[i] == 0) return i;
        }
        return -1;
    }

    void insert2Open(Node *head, Node *newNode) {
        Node *spot = findSpot(head, newNode);
        newNode->next = spot->next;
        spot->next = newNode;
    }

    Node* findSpot(Node *head, Node *newNode) {
        Node *spot = head;
        while (spot->next != NULL && spot->next->jobTime > newNode->jobTime) {
            spot = spot->next;
        }
        return spot;
    }

    void printList() {
        Node *spot = open;
        do {
            cout << spot->jobID << ", " << spot->jobTime << ", " << spot->next->jobID << " --> ";
            spot = spot->next;
            if(spot->next == NULL) {
                cout << spot->jobID << ", " << spot->jobTime << ", NULL"  << " --> NULL\n";
            }
        } while(spot->next != NULL);
    }

    boolean checkJobDone() {
        for (int i = 1; i < numNodes + 1; i++) {
            if (jobDone[i] == 0) return true;
        }
        return false;
    }

};

int main(int argc, char const *argv[]) {
    ifstream inFile1, inFile2;
    ofstream outFile;
    int numNodes, procGiven, procUsed = 0, currentTime = 0, orphanNode = 0;
    Scheduling test = Scheduling();

    inFile1.open(argv[1]);
    inFile2.open(argv[2]);
    outFile.open(argv[3]);

    inFile1 >> numNodes;
    test.adjacencyMatrix = new int*[test.numNodes + 1];
    for (int i = 0; i < numNodes + 1; i++) test.adjacencyMatrix[i] = new int[numNodes + 1];
    for (int i = 0; i < numNodes + 1; i++) {
        for (int j = 0; j < numNodes + 1; j++) {
            test.adjacencyMatrix[i][j] = 0;
        }
    }
    test.numNodes = numNodes;
    test.loadMatrix(inFile1);
    test.totalJobTimes = test.computTotalTimes(inFile2);

    //cout << "Enter processor time: ";
    //cin >> procGiven;
    if (procGiven <= 0) {
        cout << "Processors cannot be less than 1.\n";
        exit(1);
    }
    if (procGiven > test.numNodes) {
        procGiven = test.numNodes;
    }

    test.initializeArrays();

    while (test.checkJobDone()) {
        while (orphanNode != -1 ) {
            orphanNode = test.getUnmarkedOrphan();
            if (orphanNode == -1) break;
            test.jobMarked[orphanNode] = 1;
            Scheduling::Node *newNode = new Scheduling::Node();
            newNode->jobID = orphanNode;
            newNode->jobTime = test.jobTimeAry[orphanNode];
            cout << newNode->jobID << newNode->jobTime << endl;
            test.insert2Open(test.open, newNode);
        }
        test.printList();

        
    }


    inFile1.close();
    inFile2.close();
    outFile.close();
    return 0;
}
