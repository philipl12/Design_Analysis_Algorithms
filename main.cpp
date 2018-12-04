#include <iostream>
#include <fstream>
using namespace std;

int main(int argc, char const *argv[]) {
    ifstream input1;
    ofstream outFile1, outFile2;
    int input2 = 0, input3 = 0;

    input1.open(argv[1]);
    outFile1.open(argv[2]);
    outFile2.open(argv[3]);


    input1.close();
    outFile1.close();
    outFile2.close();
    return 0;
}
