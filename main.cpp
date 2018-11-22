#include <iostream>
#include <fstream>
#include <bits/stdc++.h>
using namespace std;

class Scheduling {
public:
    class Node {
    public:
        int jobID, jobTime;
        Node *next;

        Node() {
            jobID = 0;
            jobTime = 0;
            next = NULL;
        }
    };

    int numNodes, totalJobTimes;
    int **adjacencyMatrix, **scheduleTable;
    int *jobTimeAry, *processJob, *processTime, *parentCount, *kidCount, *jobDone, *jobMarked;
    Node *open;

    Scheduling() {
        open = new Node();
    }

    bool checkJobDone() {
        for (int i = 1; i < numNodes + 1; i++) {
            if (jobDone[i] <= 0) return false;
        }
        return true;
    }

    void loadMatrix(ifstream &inFile1) {
        int i = 0, j = 0;

        while (inFile1 >> i >> j) {
            adjacencyMatrix[i][j] = 1;
        }
    }

    int computTotalTimes(ifstream &inFile2) {
        int totalTimes = 0, job, time, garbage;
        jobTimeAry = new int[numNodes + 1]();

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
        for (int i = 0; i < numNodes + 1; i++) {
            scheduleTable[i] = new int[totalJobTimes + 1];
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

    Node* removeNode() {
        Node *removedNode = open->next;
        open->next = open->next->next;
        removedNode->next = NULL;
        return removedNode;
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

    int findProcessor(int procGiven) {
        for (int i = 1; i < procGiven + 1; i++) {
            if (processTime[i] <= 0) return i;
        }
        return -1;
    }

    void updateTable(int availProc, int currentTime, Node *newJob) {

        cout << availProc << ' ' << newJob->jobTime << ' ' << currentTime << endl;

        for (int i = currentTime; i < (currentTime + newJob->jobTime); i++) {
            scheduleTable[availProc][i] = newJob->jobID;
        }
/*
        for (int i = 1; i < numNodes + 1; i++) {
            for (int j = 1; j < totalJobTimes + 1; j++) {
                cout << scheduleTable[i][j] << ' ';
            }
            cout << endl;
        }
*/
    }

    bool checkAllJobs() {
//        for (int i = 0; i < procGiven; i++) {
//            if (scheduleTable[i][currentTime] != 0) return false;
//        }

        for (int i = 1; i < numNodes + 1; i++) {
            if(jobMarked[i] != 0) return false;
        }
        return true;
    }

    int checkCycle(int currentTime, int procGiven) {
        if (open->next == NULL && !checkJobDone() && checkAllJobs()) return 1;
        return 0;
    }

    void printTable(ofstream &outFile, int procGiven, int currentTime) {
        for (int i = 0; i <= procGiven; i++) {
            if (i > 0) outFile << "P(" << i << ")|";
            else { outFile << "     "; }
            for (int j = 0; j <= currentTime; j++) {
                if (i == 0) outFile << "-" << j << "--";
                else if (scheduleTable[i][j] != 0) {
                    outFile << " " << scheduleTable[i][j] << " |";
                }
                else { outFile << " - |"; }
            }
            outFile << endl;
        }
    }

    int findDoneJob(int procGiven) {
        int jobID = -1;
/*
        for (int i = 1; i < procGiven + 1; i++) {
            cout << processTime[i] << "  " << i << " process time in findDoneJob\n";
        }
*/
        for (int i = 1; i < procGiven + 1; i++) {
//            cout << processTime[i] << endl;

            if (processTime[i] == 0 && processJob[i] > 0) {
                jobID = processJob[i];
                processJob[i] = 0;
//                cout << jobID << " jobID in findDoneJob\n";
                return jobID;
            }

        }
        return -1;
    }

    void deleteNode(int job) {
        jobDone[job] = 1;
    }

    void deleteEdge(int job) {
        for (int i = 1; i < numNodes + 1; i++) {
            if (adjacencyMatrix[job][i] > 0) {
                parentCount[i]--;
                kidCount[job] = 0;
            }
        }
    }
};

int main(int argc, char const *argv[]) {
    ifstream inFile1, inFile2;
    ofstream outFile1, outFile2;
    int job = 0, numNodes = 0, availProc = 0, procGiven = 0, procUsed = 0, currentTime = 0, orphanNode = 0;
    Scheduling test = Scheduling();

    inFile1.open(argv[1]);
    inFile2.open(argv[2]);
    outFile1.open(argv[3]);
    outFile2.open(argv[4]);

    inFile1 >> numNodes;
    test.adjacencyMatrix = new int*[numNodes + 1];
    for (int i = 0; i < numNodes + 1; i++) test.adjacencyMatrix[i] = new int[numNodes + 1]();

    test.numNodes = numNodes;
    test.loadMatrix(inFile1);
    test.totalJobTimes = test.computTotalTimes(inFile2);

    cout << "Enter processor time: ";
    cin >> procGiven;
    if (procGiven <= 0) {
        cout << "Processors cannot be less than 1.\n";
        exit(1);
    }
    if (procGiven > test.numNodes) {
        procGiven = test.numNodes;
    }

    test.initializeArrays();

    while (!test.checkJobDone()) {
        while (orphanNode != -1) {
            orphanNode = test.getUnmarkedOrphan();
            if (orphanNode == -1) break;
            test.jobMarked[orphanNode] = 1;
            Scheduling::Node *newNode = new Scheduling::Node();
            newNode->jobID = orphanNode;
            newNode->jobTime = test.jobTimeAry[orphanNode];
            test.insert2Open(test.open, newNode);
        }
        test.printList();

        while (test.open->next != NULL && procUsed < procGiven) {
            availProc = test.findProcessor(procGiven);
            if (availProc > 0) {
                procUsed++;
                Scheduling::Node *newJob = test.removeNode();
                test.processJob[availProc] = newJob->jobID;
                test.processTime[availProc] = newJob->jobTime;
                test.updateTable(availProc, currentTime, newJob);
            }
        }

        if (test.checkCycle(currentTime, procGiven) >= 1) {
            cout << "Cycle in the graph\n";
            exit(1);
        }

        test.printTable(outFile1, procGiven, currentTime);
        currentTime++;
        for (int i = 1; i < procGiven + 1; i++) {
            if(test.jobMarked[i] > 0 && test.processTime[i] > 0) {

//                procUsed--;

                test.processTime[i]--;
            }
        }

        for (int i = 1; i < numNodes + 1; i++) {
            job = test.findDoneJob(procGiven);
//            cout << job << " job\n";
            if (job != -1) {
                test.deleteNode(job);
//                cout << "deletedNode\n";
                test.deleteEdge(job);
//                cout << "deletedEdge\n";
            }
        }

    }

    test.printTable(outFile1, procGiven, currentTime);
    cout << "print table outside of loop\n";

    inFile1.close();
    inFile2.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
