#include <stdio.h>
#include <cstdlib>
#include <cstring>
#include <cassert>
using namespace std;

#define MAXN 1000005
int a[MAXN],n;

void input(){
	int i;
	scanf("%d",&n);
	assert(n<MAXN);
	for (i=0;i<n;i++)
		scanf("%d",a+i);
}

void swap(int *a, int x, int y){
	int t=a[x];
	a[x]=a[y];
	a[y]=t;
}

void qsort(int *a, int l, int r){
	if (l>=r) return;
	int i,m,t;
	m=l;
	t=rand()%(r-l)+l;
	swap(a,l,t);
	for (i=l+1;i<=r;i++){
		if (a[i]<=a[l]){
			m++;
			swap(a,m,i);
		}
	}
	swap(a,l,m);
	qsort(a,l,m-1);
	qsort(a,m+1,r);
}

void solve(){
	int i;
	srand(123213);
	qsort(a,0,n-1);
	for (i=0;i<n;i++)
		printf("%d\t",a[i]);
	printf("\n");
}

int main(){
	while (1){
		input();
		solve();
	}
	return 0;
}