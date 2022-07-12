#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdbool.h>

//remove_extra starting zeros for SUB

struct Node{
char data;
struct Node* next;
};





char* substr(const char *src, int m, int n)
{
int len = n - m;
char *dest = (char*)malloc(sizeof(char) * (len + 1));
for (int i = m; i < n && (*(src + i) != '\0'); i++){
*dest = *(src + i);
dest++;
}
*dest = '\0';
return dest - len;
}


void convert_to_string(char* s, struct Node* h1){
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
th1 = h1;
int i = 0;
while(th1!=NULL){
s[i] = th1 -> data;
i++;
th1 = th1 -> next;
}
}

struct Node* convert_to_ll(char* s){
struct Node* t = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th = NULL;
int i = 0;
while(i<strlen(s)){
if(th==NULL){
t -> data = s[i++];
th = t;
}
else{
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = s[i++];
th -> next = n;
th = th -> next;
}
}
return t;
}






void print_list(struct Node* h){
struct Node* th = (struct Node*)malloc(sizeof(struct Node*));
th = h;
while(th!=NULL){
printf("%c",th->data);
th = th->next;
}
}

int size(struct Node* h){
struct Node* th = (struct Node*)malloc(sizeof(struct Node*));
th = h;
int count = 0;
while(th!=NULL){
count++;
th = th -> next;
}
return count;
}

void copy(struct Node* h3, struct Node* h1){
struct Node* th = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th3 = NULL;
th = h1;
while(th!=NULL){
if(th3==NULL){
h3 -> data = th -> data;
th3 = h3;
}
else{
struct Node* t = (struct Node*)malloc(sizeof(struct Node*));
t -> data = th -> data;
th3 -> next = t;
th3 = th3 -> next;
}
th = th -> next;
}

}

bool is_equal(struct Node* h1, struct Node* h2){
if(size(h1)!=size(h2))
	return false;
else{
if(h1 -> data == h2 -> data)
	return true;
else
	return false;
}
}

char get(struct Node* h, int idx){
struct Node* th = (struct Node*)malloc(sizeof(struct Node*));
th = h;
while(idx!=0){
idx--;
th = th -> next;
}
return th -> data;
}

void put(struct Node* h, int idx, char c){
struct Node* th = (struct Node*)malloc(sizeof(struct Node*));
th = h;
while(idx!=0){
idx--;
th = th -> next;
}
th -> data = c;
}

struct Node* reverse(struct Node* h){
struct Node* t = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th = h;
while(th->next!=NULL){
t->data = th->data;
th = th->next;
struct Node* temp = (struct Node*)malloc(sizeof(struct Node*));
temp->next = t;
t = temp;
}
t -> data = th -> data;
th = th -> next;
return t;
}

bool check_neg (struct Node* h){
if(h->data == '-')
	return true;
else
	return false;
}

void remove_sign(struct Node* h){
//not chcking if its negative or positive
h = h-> next;
}

struct Node* remove_extra(struct Node* h){

struct Node* t = (struct Node*)malloc(sizeof(struct Node*));
t = h;
while(t!=NULL){
if(t -> data == '0'){
h = h -> next;
t = h;
}
else
	break;
}
if(t==NULL){
h = (struct Node*)malloc(sizeof(struct Node*));
h -> data = '0';
}
return h;
}

void match_decimal(struct Node* h1, struct Node* h2, int *f1, int *f2){
if(*f1!=*f2){

struct Node* t1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* t2 = (struct Node*)malloc(sizeof(struct Node*));

t1 = h1;
t2 = h2;

while(t1 -> next != NULL){
t1 = t1 -> next;
}

while(t2 -> next != NULL){
t2 = t2 -> next;
}

if(*f1 > *f2){
if(*f2!=0){
for(int i=0;i<*f1-*f2;i++){
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = '0';
t2 -> next = n;
t2 = t2 -> next;
}}
else{
for(int i = 1;i<*f1;i++){
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = '0';
t2 -> next = n;
t2 = t2 -> next;
}
}
}

else{
if(*f1!=0){
for(int i=0;i<*f2-*f1;i++){
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = '0';
t1 -> next = n;
t1 = t1 -> next;
}}
else{
for(int i=1;i<*f2;i++){
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = '0';
t1 -> next = n;
t1 = t1 -> next;
}
}
}
}
h1 = remove_extra(h1);
h2 = remove_extra(h2);
}

void take_input(struct Node* h1, struct Node* h2, int *f1, int *f2){
struct Node* th1 = NULL;
struct Node* th2 = NULL;
char c;
*f1 = 0;
*f2 = 0;
int count = 0;
int t = 1;
c = getchar();
while(c!='\n'){

if(t==1){
if(c == ' '){
t = 2;
count = 0;
}
else if(c == '.'){
*f1 = 1;
}
else{
if(th1 == NULL){
h1 -> data = c;
h1 -> next = NULL;
th1 = h1;
if(*f1>0)
	*f1 = *f1 + 1;
}
else{
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = c;
n -> next = NULL;
th1 -> next = n;
th1 = th1 -> next;
if(*f1>0)
	*f1 = *f1 + 1;
}
}
}
else if(t==2){
if(c == '.'){
*f2 = 1;
}
else{
if(th2 == NULL){
h2 -> data = c;
h2 -> next = NULL;
th2 = h2;
if(*f2>0)
	*f2 = *f2 + 1;
}
else{
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = c;
n -> next = NULL;
th2 -> next = n;
th2 = th2 -> next;
if(*f2>0)
	*f2 = *f2 + 1;
}
}
}
c = getchar();
}
}

struct Node* ADD(struct Node* h1, struct Node* h2){
//printf("\n%c\n",h1->data);
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* h = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th = NULL;
th1 = h1;
th2 = h2;
int carry = 0;
int count = 0;
while(th1!=NULL && th2!=NULL){
count++;
int ans = (th1->data - '0') + (th2->data - '0') + carry;
if(th == NULL){
h -> data = (ans % 10) + '0';
h -> next = NULL;
th = h;
carry = ans/10;
}
else{
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = (ans %10) + '0';
th -> next = n;
th = th -> next;
carry = ans / 10;
}
th1 = th1 -> next;
th2 = th2 -> next;
}

//
if(th1!=NULL || th2!=NULL){
if(carry == 0)
if(th2 == NULL)
	th -> next = th1;
else
	th -> next = th2;
else{
if(th1!=NULL){
while(th1!=NULL && carry == 1){
int ans = (th1 -> data - '0') + carry;

struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = (ans %10) + '0';
th -> next = n;
th = th -> next;
carry = ans / 10;

th1 = th1 -> next;
}
if(th1!=NULL)
	th -> next = th1;
}

else{
while(th2!=NULL && carry == 1){
int ans = (th2 -> data - '0') + carry;

struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = (ans %10) + '0';
th -> next = n;
th = th -> next;
carry = ans / 10;

th2 = th2 -> next;
}
if(th2!=NULL)
	th -> next = th2;
}
}
}

h = reverse(h);
return h;
}

int compare(struct Node* h1, struct Node* h2){
//assuming initially they are reversed
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
th1 = reverse(h1);
th2 = reverse(h2);
if(size(th1)>size(th2)){
return 1;
}
else if(size(th1)<size(th2)){
return -1;
}
else{
while(th1!=NULL && th2!=NULL){
if((th1->data - '0') > (th2 -> data - '0'))
	return 1;
else if((th1->data - '0') < (th2 -> data - '0'))
	return -1;
else{
th1 = th1 -> next;
th2 = th2 -> next;
}
}
if(th1==NULL && th2==NULL)
	return 0;
}
}

struct Node* SUB(struct Node* h1, struct Node* h2){
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* h = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th = NULL;

int carry = 0;
int flag = 0;
if(compare(h1,h2)<0){
flag = 1;
struct Node* temp = h1;
h1 = h2;
h2 = temp;
}

th1 = h1;
th2 = h2;

while(th1!=NULL && th2!=NULL){
int ans = (th1->data - '0') - (th2->data - '0') - carry;
int sub;
if(ans < 0){
sub = 10 + ans;
carry = 1;
}
else{
sub = ans;
carry = 0;
}
if(th==NULL){
h -> data = sub + '0';
h -> next = NULL;
th = h;
}
else{
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = sub + '0';
th -> next = n;
th = th -> next;
}
th1 = th1 -> next;
th2 = th2 -> next;
}

if(th1!=NULL){
if(carry == 0)
	th -> next = th1;
else{
while(th1!=NULL && carry == 1){
int ans = (th1 -> data - '0') - carry;
int sub;
if(ans < 0){
sub = 10 + ans;
carry = 1;
}
else{
sub = ans;
carry = 0;
}
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = sub + '0';
th -> next = n;
th = th -> next;
th1 = th1 -> next;
}
if(th1!=NULL)
	th -> next = th1;
}
}
h = reverse(h);
h = remove_extra(h);
if(flag==1){
//printf("checking\n");
struct Node* cp = (struct Node*)malloc(sizeof(struct Node*));
cp -> data = '-';
cp -> next = h;
h = cp;
}
return h;
}

struct Node* MUL(struct Node* h1, struct Node* h2){
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* h3 = NULL;
struct Node* th3 = NULL;
th1 = h1;
th2 = h2;
int t = size(th1) + size(th2);
while(t>0){
if(th3 == NULL){
h3 = (struct Node*)malloc(sizeof(struct Node*));
h3 -> data = '0';
th3 = h3;
}
else{
struct Node* n = (struct Node*)malloc(sizeof(struct Node*));
n -> data = '0';
th3 -> next = n;
th3 = th3 -> next;
}
t--;
}

int i =0, j =0, carry = 0;
while(th1!=NULL){
th2 = h2;
int carry = 0;
int m1 = th1 -> data - '0';
int c1 = 0;
j = 0;
while(th2!=NULL){
c1++;
int m2 = th2 -> data - '0';

int sum = m1*m2 + (get(h3,i+j) - '0') + carry; 
carry = sum/10; 
put(h3,i+j,(sum % 10) + '0'); 
th2 = th2 -> next;
j++;

}
if (carry > 0) 
put(h3,i+j,(get(h3,i+j)-'0'+carry) + '0');
th1 = th1 -> next;
i++;
}
h3 = reverse(h3);
h3 = remove_extra(h3);
return h3;
}

struct Node* div_small(struct Node* d1, struct Node* d2){
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* d3 = (struct Node*)malloc(sizeof(struct Node*));
d1 = remove_extra(d1);
d2 = remove_extra(d2);
th1 = d1;
th2 = d2;

d3 -> data = '0';
for(int i = 1; i< 10; i++){
struct Node* tm = (struct Node*)malloc(sizeof(struct Node*));
tm -> data = i + '0';
if(compare(reverse(th1),reverse(MUL(reverse(th2),reverse(tm))))==0){
d3 -> data = i + '0';
return d3;
}
else if(compare(reverse(th1),reverse(MUL(reverse(th2),reverse(tm))))<0){
break;
}
d3 -> data = i + '0';
} 

return d3;
}

struct Node* rem_small(struct Node* d1, struct Node* d2, char c){
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th3 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* d3 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* d4 = (struct Node*)malloc(sizeof(struct Node*));
th1 = reverse(d1);
th2 = reverse(d2);
th3 -> data = c;
th3 -> next = NULL;
th3 = reverse(th3);

d3 = MUL(th2,th3);
d4 = SUB(th1,reverse(d3));

return d4;
}

struct Node* DIV(struct Node* h1, struct Node* h2){
//if divisor is less than dividend
struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th2 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* h3 = (struct Node*)malloc(sizeof(struct Node*));
th1 = h1;
th2 = h2;
struct Node* th3 = NULL;
struct Node* mm =  (struct Node*)malloc(sizeof(struct Node*));
struct Node* tm = NULL;
mm -> data = th1 -> data;
tm = mm;
th1 = th1 -> next;
int i = 0;
while(compare(reverse(mm),reverse(th2))<0 && th1 != NULL){
struct Node* n =  (struct Node*)malloc(sizeof(struct Node*));
n -> data = th1 -> data;
tm -> next = n;
tm = tm -> next;
th1 = th1 -> next;
i++;
}
int test = 0;
while(i<size(h1)){

struct Node* th4 = (struct Node*)malloc(sizeof(struct Node*));
th4 = div_small(mm,th2);
test++;
if(th3 == NULL){
h3 = th4;
th3 = h3;
}
else{
th3 -> next = th4;
while(th3->next!=NULL){
th3 = th3 -> next;
}
}
mm = rem_small(mm,th2,th4->data);
mm = remove_extra(mm);
tm = mm;
while(tm->next != NULL){
tm = tm -> next;
} 
i++;


if(th1 != NULL){
struct Node* m =  (struct Node*)malloc(sizeof(struct Node*));
m -> data = th1 -> data;
tm -> next = m;
tm = tm -> next;
th1 = th1 -> next;
}


}

int f = 0;

struct Node* ch = (struct Node*)malloc(sizeof(struct Node*));
ch -> data = '0';

if(th1==NULL){

if((size(mm) == size(ch)) && (ch -> data == mm -> data)){

return h3;
}
else{
int k = 0;

while(k<20){
tm = mm;
while(tm -> next != NULL){
tm = tm -> next;
}
struct Node* ne = (struct Node*)malloc(sizeof(struct Node*));
ne -> data = '0';
tm -> next = ne;
struct Node* th4 = (struct Node*)malloc(sizeof(struct Node*));
th4 = div_small(mm,th2);
th3 -> next = th4;
while(th3->next!=NULL){
th3 = th3 -> next;
}
mm = rem_small(mm,th2,th4->data);
mm = remove_extra(mm);
k++;
}

}

}
//if less than 20 decimal places
return h3;

}

struct Node* sq_small(struct Node* h3,struct Node* q, struct Node* qh){

struct Node* i = (struct Node*)malloc(sizeof(struct Node*));
struct Node* j = (struct Node*)malloc(sizeof(struct Node*));
struct Node* temp = (struct Node*)malloc(sizeof(struct Node*));
struct Node* templ = (struct Node*)malloc(sizeof(struct Node*));

temp = q;
templ = temp;
i -> data = '0';
struct Node* one = (struct Node*)malloc(sizeof(struct Node*));
one -> data = '1';
if(qh == NULL){
j = MUL(i,i);
int count = 0;
//print_list(h3);
//printf("ch\n");
int flag = 0;
while(compare(reverse(j),reverse(h3))<=0){
    
if(count<9){
i = ADD(i,one);
j = MUL(i,i);
count++;
}
else if(count==9){
    flag = 1;
break;
}
//printf("%d  ",count);
   // print_list(j);
    //printf("erftrg\n");
}
//printf("%d\n",count);
if(flag!=1){
int p = i->data - '0';
p--;
i -> data = p + '0';
}
return i;
}

else{
    
while(templ -> next != NULL){
templ = templ -> next;
}
templ -> next = i;
templ = templ -> next;
j = MUL(reverse(temp),i);
int count = 0;
int flag = 0;
while(compare(reverse(j),reverse(h3))<=0){
if(count<9){
count++;
i = ADD(i,one);
templ -> data = i -> data;
j = MUL(reverse(temp),i);
}
else if(count==9){
    flag = 1;
break;
}
}
//printf("%d\n",count);
if(flag!=1){
int p = i->data - '0';
p--;
i -> data = p + '0';
}
return i;
}

return i;

}


struct Node* SQRT(struct Node* h1){

struct Node* th1 = (struct Node*)malloc(sizeof(struct Node*));
th1 = h1;
struct Node* h3 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* h4 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* h5 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th3 = NULL;
struct Node* q = (struct Node*)malloc(sizeof(struct Node*));
struct Node* d = (struct Node*)malloc(sizeof(struct Node*));
struct Node* qh = NULL;
struct Node* dh = NULL;
struct Node* two = (struct Node*)malloc(sizeof(struct Node*));
struct Node* one = (struct Node*)malloc(sizeof(struct Node*));
struct Node* t5 = (struct Node*)malloc(sizeof(struct Node*));
two -> data = '2';
one -> data = '1';
//printf("size = %d\n",size(h1));
int sz = size(h1);
if(size(h1) % 2 == 0){
h3 -> data = h1 -> data;
th3 = h3;
h1 = h1 -> next;
struct Node* t = (struct Node*)malloc(sizeof(struct Node*));
t -> data = h1 -> data;
th3 -> next = t;
th3 = th3 -> next;
h1 = h1 -> next;
}
else{
h3 -> data = h1 -> data;
th3 = h3;
h1 = h1 -> next;
}

int ptr = 0;
int counter = 0;
while(2*counter<sz){
    counter++;
//printf("while\n");
h3 = remove_extra(h3);
//print_list(h3);
//printf(" ");
//print_list(d);
//printf("\n");
h4 = sq_small(h3,d,dh);
//print_list(h4);
//printf("\n");
if(qh == NULL){
q -> data = h4 -> data;
qh = q;
d -> data = h4 -> data;
dh = d;
}
else{
  //  printf("%c  ",h4 -> data);
struct Node* qt = (struct Node*)malloc(sizeof(struct Node*));
qt -> data = h4 -> data;
qh -> next = qt;
qh = qh -> next;
struct Node* dt = (struct Node*)malloc(sizeof(struct Node*));
dt -> data = h4 -> data;
dh -> next = dt;
//dh -> data = h4 -> data;
dh = dh -> next;
}
if(ptr==1)
d = SUB(reverse(d),one);
else
ptr = 1;
//print_list(d);
//printf("    ");
//print_list(h4);
//printf("\n");
h3 = SUB(reverse(h3),(reverse(MUL(reverse(d),reverse(h4)))));
h3 = remove_extra(h3);
//print_list(h3);
//printf("e\n");

struct Node* ttt = NULL;
ttt = h3;
while(ttt -> next != NULL){
ttt = ttt -> next;
}
if(h1!=NULL){
struct Node* tm = (struct Node*)malloc(sizeof(struct Node*));
tm -> data = h1 -> data;
ttt -> next = tm;
ttt = ttt -> next;
h1 = h1 -> next;

struct Node* tr = (struct Node*)malloc(sizeof(struct Node*));
tr -> data = h1 -> data;
ttt -> next = tr;
ttt = ttt -> next;
h1 = h1 -> next;
}
d = MUL(reverse(q),two);
}

//printf("\ncounter = %d\n",counter);

return q;

}

void arithmetic(struct Node* h1, struct Node* h2, char* s, int *f1, int *f2){
//finally place decimal point
//if any input is 0 / null
//printf("%s\n",s);
char c;
int pq = strlen(s);
if(strcmp(s,"ADD") == 0){
int cas = 0;
if(check_neg(h1)){
remove_sign(h1);
if(check_neg(h2)){
remove_sign(h2);
cas = 1;
}
else{
cas = -1;
}
}
else{
if(check_neg(h2)){
remove_sign(h2);
cas = -2;
}
else{
cas = 2;
}
}
match_decimal(h1,h2,f1,f2);

h1 = reverse(h1);
h2 = reverse(h2);
//printf("first list\n");
//print_list(h1);
//printf("second list\n");
//print_list(h2);
struct Node* h3 = (struct Node*)malloc((sizeof(struct Node*)));
if(cas == 1)
{
h3 = ADD(h1,h2);
struct Node* n = (struct Node*)malloc((sizeof(struct Node*)));
n -> data = '-';
n -> next = h3;
h3 = n;
}
else if(cas == 2){
h3 = ADD(h1,h2);
}
else if(cas == -1){
h3 = SUB(h2,h1);
}
else if(cas == -2){
h3 = SUB(h1,h2);
}
struct Node* jk = h3;
if(*f1!=0 || *f2!=0){
if(*f2==0){
int l = *f1 - 1;
l = size(h3) - l -1;
//printf("\n %d ans",l);
for(int i=0;i<l;i++){
jk = jk -> next;
}
struct Node* pt = (struct Node*)malloc(sizeof(struct Node*));
pt -> data = '.';
struct Node* pp = (struct Node*)malloc(sizeof(struct Node*));
pp = jk -> next;
jk -> next = pt;
pt -> next = pp;
}
else if(*f1==0){
int l = *f2 - 1;
l = size(h3) - l -1;
//printf("\n %d ans",l);
for(int i=0;i<l;i++){
jk = jk -> next;
}
struct Node* pt = (struct Node*)malloc(sizeof(struct Node*));
pt -> data = '.';
struct Node* pp = (struct Node*)malloc(sizeof(struct Node*));
pp = jk -> next;
jk -> next = pt;
pt -> next = pp;
}
else{
int max = *f1;
if(max<*f2)
max = *f2;
int l = max - 1;
l = size(h3) - l -1;
//printf("\n %d ans",l);
for(int i=0;i<l;i++){
jk = jk -> next;
}
struct Node* pt = (struct Node*)malloc(sizeof(struct Node*));
pt -> data = '.';
struct Node* pp = (struct Node*)malloc(sizeof(struct Node*));
pp = jk -> next;
jk -> next = pt;
pt -> next = pp;
}
}
//printf("final list\n");
print_list(h3);
printf("\n");
}
else if(strcmp(s,"SUB") == 0){
int cas = 0;
if(check_neg(h1)){
remove_sign(h1);
if(check_neg(h2)){
remove_sign(h2);
cas = 1;
}
else{
cas = -1;
}
}
else{
if(check_neg(h2)){
remove_sign(h2);
cas = -2;
}
else{
cas = 2;
}
}
match_decimal(h1,h2,f1,f2);

h1 = reverse(h1);
h2 = reverse(h2);
/*printf("first list\n");
print_list(h1);
printf("second list\n");
print_list(h2);*/
struct Node* h3 = (struct Node*)malloc((sizeof(struct Node*)));
if(cas == 1)
{
h3 = SUB(h2,h1);
}
else if(cas == -1){
h3 = ADD(h1,h2);
struct Node* n = (struct Node*)malloc((sizeof(struct Node*)));
n -> data = '-';
n -> next = h3;
h3 = n;
}
else if(cas == 2){
h3 = SUB(h1,h2);
}
else if(cas == -2){
h3 = ADD(h1,h2);
}
struct Node* jk = h3;
if(*f1!=0 || *f2!=0){
if(*f2==0){
int l = *f1 - 1;
l = size(h3) - l -1;
//printf("\n %d ans",l);
for(int i=0;i<l;i++){
jk = jk -> next;
}
struct Node* pt = (struct Node*)malloc(sizeof(struct Node*));
pt -> data = '.';
struct Node* pp = (struct Node*)malloc(sizeof(struct Node*));
pp = jk -> next;
jk -> next = pt;
pt -> next = pp;
}
else if(*f1==0){
int l = *f2 - 1;
l = size(h3) - l -1;
//printf("\n %d ans",l);
for(int i=0;i<l;i++){
jk = jk -> next;
}
struct Node* pt = (struct Node*)malloc(sizeof(struct Node*));
pt -> data = '.';
struct Node* pp = (struct Node*)malloc(sizeof(struct Node*));
pp = jk -> next;
jk -> next = pt;
pt -> next = pp;
}
else{
int max = *f1;
if(max<*f2)
max = *f2;
int l = max - 1;
l = size(h3) - l -1;
//printf("\n %d ans",l);
for(int i=0;i<l;i++){
jk = jk -> next;
}
struct Node* pt = (struct Node*)malloc(sizeof(struct Node*));
pt -> data = '.';
struct Node* pp = (struct Node*)malloc(sizeof(struct Node*));
pp = jk -> next;
jk -> next = pt;
pt -> next = pp;
}
}
//printf("final list\n");
print_list(h3);
printf("\n");
}
else if(strcmp(s,"MUL") == 0){
char c = '.';
if(check_neg(h1)){
remove_sign(h1);
if(check_neg(h2)){
remove_sign(h2);
c = '+';
}
else{
c = '-';
}
}
else{
if(check_neg(h2)){
remove_sign(h2);
c = '-';
}
else{
c = '+';
}
}
h1 = reverse(h1);
h2 = reverse(h2);
struct Node* h3 = (struct Node*)malloc((sizeof(struct Node*)));
h3 = MUL(h1,h2);

//checking decimal 
//wrong

//checking decimal
struct Node* nd = h3;
if(*f2==0 && *f1 != 0){
    //printf("kjbkrej");
    int num = *f1 - 1;
    num = size(h3) - num - 1;
    //printf("%d\n",num);
    for(int i=0;i<num;i++){
        nd = nd -> next;
    }
    struct Node* nt = (struct Node*)malloc((sizeof(struct Node*)));
    nt = nd -> next;
    struct Node* np = (struct Node*)malloc((sizeof(struct Node*)));
    np -> data = '.';
    nd -> next = np;
    np -> next = nt;
}
else if(*f1==0 && *f2!=0){
    int num = *f2 - 1;
    num = size(h3) - num - 1;
    //printf("%d\n",num);
    for(int i=0;i<num;i++){
        nd = nd -> next;
    }
    struct Node* nt = (struct Node*)malloc((sizeof(struct Node*)));
    nt = nd -> next;
    struct Node* np = (struct Node*)malloc((sizeof(struct Node*)));
    np -> data = '.';
    nd -> next = np;
    np -> next = nt;
}
else if(*f1!=0 && *f2!=0){
    
    int num = *f1 + *f2 - 2;
   // printf("%d",num);
    num = size(h3) - num - 1;
   // printf("%d\n",num);
    if(num>=0){
    for(int i=0;i<num;i++){
        nd = nd -> next;
    }
    struct Node* nt = (struct Node*)malloc((sizeof(struct Node*)));
    nt = nd -> next;
    struct Node* np = (struct Node*)malloc((sizeof(struct Node*)));
    np -> data = '.';
    nd -> next = np;
    np -> next = nt;}
    else{
        for(int i=num;i<0;i++){
            struct Node* z = (struct Node*)malloc(sizeof(struct Node*));
            z -> data = '0';
            z -> next = nd;
            h3 = z;
            nd = h3;
        }
        struct Node* nt = (struct Node*)malloc((sizeof(struct Node*)));
    nt = nd -> next;
    struct Node* np = (struct Node*)malloc((sizeof(struct Node*)));
    np -> data = '.';
    nd -> next = np;
    np -> next = nt;
    }
}
//checking decimal

if(c == '-'){
struct Node* n = (struct Node*)malloc((sizeof(struct Node*)));
n -> data = c;
n -> next = h3;
h3 = n;
}
}
else if(strcmp(s,"DIV") == 0){
char c = '.';
if(check_neg(h1)){
remove_sign(h1);
if(check_neg(h2)){
remove_sign(h2);
c = '+';
}
else{
c = '-';
}
}
else{
if(check_neg(h2)){
remove_sign(h2);
c = '-';
}
else{
c = '+';
}
}

struct Node* h3 = (struct Node*)malloc((sizeof(struct Node*)));
h3 = DIV(h1,h2);
struct Node* ph3 = h3;
//checking decimal for division

if(c == '-'){
struct Node* n = (struct Node*)malloc((sizeof(struct Node*)));
n -> data = c;
n -> next = h3;
h3 = n;
}
//printf("\n");
remove_extra(h3);
//trailing zeros
print_list(h3);
printf("\n");
}
else if(strcmp(s,"ABS") == 0){
if(h1 -> data = '-'){
h1 = h1 -> next;
}
print_list(h1);
printf("\n");
}
else if(strcmp(s,"SQRT") == 0){
if(check_neg(h1))
	printf("error\n");
else{
struct Node* temph = (struct Node*)malloc(sizeof(struct Node*));
temph = h1;
while(temph->next != NULL){
temph = temph ->next;
}
int i = 2* *f1;
while(i<40){
struct Node* z = (struct Node*)malloc(sizeof(struct Node*));
z -> data = '0';
temph -> next = z;
temph = temph -> next;
i = i + 1;
}
struct Node* h3 = (struct Node*)malloc(sizeof(struct Node*));
h3 = SQRT(h1);
struct Node* st = h3;
//add decimal point at last places
int num = size(h3) - 21;
//printf("num = %d\n",size(h3));
for(int i=0;i<num;i++){
    st = st -> next;
}
struct Node* sp = (struct Node*)malloc(sizeof(struct Node*));
sp -> data = '.';
struct Node* ss = st -> next;
st -> next = sp;
sp -> next = ss;
print_list(h3);
printf("\n");
}
}
else if(strcmp(s,"POW") == 0){
//signs of operands


/*printf("\n");
print_list(h1);
printf("\n");
print_list(h2);
printf("\n");*/
struct Node* h3 = (struct Node*)malloc(sizeof(struct Node*));
struct Node* th3 = NULL;
struct Node* ch = (struct Node*)malloc(sizeof(struct Node*));
ch -> data = '0';
struct Node* one = (struct Node*)malloc(sizeof(struct Node*));
one -> data = '1';
while(!(is_equal(ch,h2))){
if(th3 == NULL){
copy(h3,h1);
th3 = h3;
}
else{
h3 = MUL(reverse(h3),reverse(h1));
th3 = h3;
}
h2 = SUB(reverse(h2),one);
h2 = remove_extra(h2);
}
print_list(h3);
printf("\n");
}
else{
perror("invalid input");
}
}


//negatives for powers
//division of decimals 
//trailing zeros
//remove_extra
//large inputs and all kinds of inputs
//makefile

//decimal for pow - only for integers


int main(int argc, char** argsv[]){
char c;
c = getchar();
while(c != EOF){
char* s;
s = (char*)malloc((sizeof(char))*4);
s[0] = c;
c = getchar();
int temp = 1;
while(c!=' '){
s[temp] = c;
c = getchar();
temp++;
}
s = realloc(s,temp);
struct Node* h1 = (struct Node*)malloc((sizeof(struct Node*)));
struct Node* h2 = (struct Node*)malloc((sizeof(struct Node*)));
int f1, f2;
take_input(h1,h2,&f1,&f2);

arithmetic(h1, h2, s, &f1, &f2);

c = getchar();
}
return 0;
}

