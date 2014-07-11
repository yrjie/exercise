#include <stdio.h>
#include <iostream>
#include <cstring>
#include <Windows.h>
#include <cmath>
#include <cassert>
using namespace std;

#define eps 1e-8

double PI2=2*acos(-1.0);

void ratioMode(){
	__int64 i=0;
	int start, idle;
	double ratio;
	scanf("%lf",&ratio);
	if (ratio<eps||ratio>1)
		ratio=0.5;
	printf("%lf\n",ratio);
	start=GetTickCount();
	for (i=0;i<50000000;i++);
	idle=(GetTickCount()-start)*(1-ratio)/ratio;
	cout<<idle<<endl;
	while (1){
		for (i=0;i<50000000;i++);
		Sleep(idle);
	}
	/*while (1){
		start=GetTickCount();
		while ((GetTickCount()-start)<=100);
		Sleep(100);
	}*/
}

void sinMode(){
	int n, i, j, start, idle, nowI, nowN, numR, total;
	double dj=0.1, ratio[1000];
	n=500000000;
	start=GetTickCount();
	for (i=0;i<n;i++);
	idle=GetTickCount()-start;
	for (j=0;dj*j<PI2;j++)
		ratio[j]=sin(j*dj)*0.5+0.5;
	numR=j;
	j=0;
	while (1){
		j++;
		if (j==numR)
			j=0;
		//printf("%lf\n",ratio[j]);
		nowI=idle*(1-ratio[j])/ratio[j];
		total=idle/ratio[j];
		printf("%d\n",total);
		nowN=n;
		if (total>1000){
			while (total>1000){
				total>>=1;
				nowI>>=1;
				nowN>>=1;
			}
		}
		else if(total<1000){
			while (total<1000){
				total<<=1;
				nowI<<=1;
				nowN<<=1;
			}
		}
		//printf("%d\n",nowI);
		for (i=0;i<nowN;i++);
		Sleep(nowI);
	}
}

int main(){
	SetProcessAffinityMask(GetCurrentProcess(),0x00000010);
	assert(1==__rdtsc());
	ratioMode();
	//sinMode();
	return 0;
}
