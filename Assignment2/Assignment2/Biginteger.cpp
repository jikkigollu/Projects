#include <bits/stdc++.h>

using namespace std;

class BigInt{
    vector<char> digits;
    int sign;
    void trim();
public:
    BigInt();
    BigInt(long long int);
    BigInt(string&);
    BigInt(const char *);
    BigInt(const BigInt&);

    BigInt &operator=(const BigInt&);
    int operator[](const int)const;
    friend int length(const BigInt&);
    friend bool Null(const BigInt&);

    friend BigInt abs(const BigInt&);
    friend BigInt pow(const BigInt&,const BigInt&);
    friend BigInt sqrt(const BigInt&);

    friend BigInt operator-(const BigInt&);

    friend ostream& operator<<(ostream&,const BigInt&);
    friend istream& operator>>(istream&,BigInt&);

    friend BigInt &operator+=(BigInt&,const BigInt&);
    friend BigInt &operator-=(BigInt&,const BigInt&);
    friend BigInt &operator*=(BigInt&,const BigInt&);
    friend BigInt &operator/=(BigInt&,const BigInt&);
    friend BigInt &operator%=(BigInt&,const BigInt&);

    friend BigInt operator+(const BigInt&,const BigInt&);
    friend BigInt operator-(const BigInt&,const BigInt&);
    friend BigInt operator*(const BigInt&,const BigInt&);
    friend BigInt operator/(const BigInt&,const BigInt&);
    friend BigInt operator%(const BigInt&,const BigInt&);

    friend BigInt &operator++(BigInt&);
    friend BigInt operator++(BigInt&,int);
    friend BigInt &operator--(BigInt&);
    friend BigInt operator--(BigInt&,int);

    friend bool operator>(const BigInt&,const BigInt&);
    friend bool operator<(const BigInt&,const BigInt&);
    friend bool operator==(const BigInt&,const BigInt&);
    friend bool operator!=(const BigInt&,const BigInt&);
    friend bool operator>=(const BigInt&,const BigInt&);
    friend bool operator<=(const BigInt&,const BigInt&);
};

BigInt::BigInt(){
    sign=1;
}

BigInt::BigInt(long long int val){
    if(val>=0){
        sign=1;
    }
    else{
        sign=-1;
    }
    val=val*sign;
    do{
        digits.push_back('0'+val%10);
        val=val/10;
    }while(val!=0);
}

BigInt::BigInt(string& s){
    sign=1;
    for(int i=s.size()-1;i>=0;i--){
        if(isdigit(s[i])){
            digits.push_back(s[i]);
        }
        else{
            if(i==0){
                if(s[i]=='+'){
                    sign=1;
                }
                else if(s[i]=='-'){
                    sign=-1;
                }
                else{
                    throw("ERROR");
                }
            }
            else{
                throw("ERROR");
            }
        }
    }
    trim();
}

BigInt::BigInt(const char* c){
    sign=1;
    for(int i=strlen(c)-1;i>=0;i--){
        if(isdigit(c[i])){
            digits.push_back(c[i]);
        }
        else{
            if(i==0){
                if(c[i]=='+'){
                    sign=1;
                }
                else if(c[i]=='-'){
                    sign=-1;
                }
                else{
                    throw("ERROR");
                }
            }
            else{
                throw("ERROR");
            }
        }
    }
    trim();
}

BigInt::BigInt(const BigInt& a){
    digits=a.digits;
    sign=a.sign;
}

void BigInt::trim(){
    while(!digits.empty() && digits.back()=='0'){
        digits.pop_back();
    }
    if(digits.size()==0){
        digits.push_back('0');
        sign=1;
    }
}

BigInt &BigInt::operator=(const BigInt& a){
    digits=a.digits;
    sign=a.sign;
    return (*this);
}

int BigInt::operator[](const int index)const{
    if(digits.size()<=index || index<0){
        throw("ERROR");
    }
    return (digits[index]-'0');
}

int length(const BigInt& a){
    return a.digits.size();
}

bool Null(const BigInt& a){
    if(a.digits.size()==1 && a.digits[0]=='0'){
        return true;
    }
    return false;
}

BigInt operator-(const BigInt& a){
    BigInt b(a);
    b.sign=a.sign*(-1);
    return b;
}

BigInt abs(const BigInt& a){
    if(a.sign==1){
        return a;
    }
    return -a;
}

ostream& operator<<(ostream& stream,const BigInt& a){
    if(a.sign==-1){
        stream<<"-";
    }
    for(int i=length(a)-1;i>=0;i--){
        stream<<a.digits[i];
    }
    return stream;
}

istream& operator>>(istream& stream,BigInt& a){
    string s;
    stream>>s;
    a=BigInt(s);
    return stream;
}

bool operator<(const BigInt& a,const BigInt& b){
    if(a.sign!=b.sign){
        if(a.sign==1){
            return false;
        }
        return true;
    }
    if(length(a)!=length(b)){
        if(a.sign==1){
            return (length(a)<length(b));
        }
        return (length(b)<length(a));
    }
    int i=length(a)-1;
    while(i>=0){
        if(a.sign==1 && a[i]<b[i]){
            return true;
        }
        if(a.sign==1 && a[i]>b[i]){
            return false;
        }
        if(a.sign==-1 && a[i]<b[i]){
            return false;
        }
        if(a.sign==-1 && a[i]>b[i]){
            return true;
        }
        i--;
    }
    return false;
}

bool operator==(const BigInt& a,const BigInt& b){
    if(a.sign!=b.sign || length(a)!=length(b)){
        return false;
    }
    int i=length(a)-1;
    while(i>=0){
        if(a[i]!=b[i]){
            return false;
        }
        i--;
    }
    return true;
}

bool operator!=(const BigInt& a,const BigInt& b){
    return !(a==b);
}

bool operator<=(const BigInt& a,const BigInt& b){
    return (a<b || a==b);
}

bool operator>(const BigInt& a,const BigInt& b){
    return !(a<=b);
}

bool operator>=(const BigInt& a,const BigInt& b){
    return (a>b || a==b);
}

BigInt &operator+=(BigInt& a,const BigInt& b){
    if(a.sign==b.sign){
        int i=0,j=0,carry=0;
        while(i<length(a) && j<length(b)){
            int sum=a[i]+b[j]+carry;
            carry=sum/10;
            a.digits[i]=('0'+sum%10);
            i++;
            j++;
        }
        while(i<length(a)){
            int sum=a[i]+carry;
            carry=sum/10;
            a.digits[i]=('0'+sum%10);
            i++;
        }
        while(j<length(b)){
            int sum=b[j]+carry;
            carry=sum/10;
            a.digits.push_back('0'+sum%10);
            j++;
        }
        if(carry){
            a.digits.push_back('0'+carry);
        }
        return a;
    }
    if(a.sign==1){
        return a-=(-b);
    }
    return a=b-(-a);
}

BigInt &operator-=(BigInt& a,const BigInt& b){
    if(a.sign==b.sign){
        if(a.sign==1){
            if(a<b){
                return a=-(b-a);
            }
        }
        else{
            if(-a>-b){
                return a=-(-a-(-b));
            }
            else{
                return a=(-b)-(-a);
            }
        }
        int i=0,j=0,carry=0;
        while(i<length(a) && j<length(b)){
            int diff=a[i]-b[j]-carry;
            carry=0;
            if(diff<0){
                carry=1;
                diff=diff+10;
            }
            a.digits[i]=('0'+diff);
            i++;
            j++;
        }
        while(i<length(a) && carry!=0){
            int diff=a[i]-carry;
            carry=0;
            if(diff<0){
                carry=1;
                diff=diff+10;
            }
            a.digits[i]=('0'+diff);
            i++;
        }
        a.trim();
        return a;
    }
    if(a.sign==1){
        return a+=(-b);
    }
    return a=-(b+(-a));
}

BigInt &operator*=(BigInt& a,const BigInt& b){
    BigInt zero("0");
    BigInt res("0");
    if(a==zero || b==zero){
        res=zero;
    }
    else{
        int j=0;
        while(j<length(b)){
            if(b[j]==0){
                j++;
                continue;
            }
            BigInt temp;
            int num_of_zeros=j;
            while(num_of_zeros--){
                temp.digits.push_back('0');
            }
            int i=0,carry=0;
            while(i<length(a)){
                int prod=a[i]*b[j]+carry;
                carry=prod/10;
                prod=prod%10;
                temp.digits.push_back('0'+prod);
                i++;
            }
            if(carry){
                temp.digits.push_back('0'+carry);
            }
            temp.trim();
            res=res+temp;
            j++;
        }
        if(a.sign!=b.sign){
            res.sign=-1;
        }
    }
    a=res;
    return a;
}

BigInt &operator/=(BigInt& a,const BigInt& b){
    BigInt zero("0");
    if(b==zero){
        throw("ERROR");
    }
    BigInt t1=abs(a);
    BigInt t2=abs(b);
    if(t1<t2){
        a=zero;
        return a;
    }
    deque<char> dq;
    BigInt ten(10);
    BigInt temp("0");
    int i=length(t1)-1;
    while(i>=0){
        int d=t1[i];
        temp=temp*ten+BigInt(d);
        char c='0';
        while(temp>=t2){
            temp=temp-t2;
            c=c+1;
        }
        dq.push_front(c);
        i--;
    }
    a.digits.clear();
    a.digits.insert(a.digits.end(),dq.begin(),dq.end());
    a.trim();
    if(a.sign==b.sign){
        a.sign=1;
    }
    else{
        a.sign=-1;
    }
    return a;
}

BigInt &operator%=(BigInt& a,const BigInt& b){
    return a-=((a/b)*b);
}

BigInt operator+(const BigInt& a,const BigInt& b){
    BigInt c(a);
    c+=b;
    return c;
}

BigInt operator-(const BigInt& a,const BigInt& b){
    BigInt c(a);
    c-=b;
    return c;
}

BigInt operator*(const BigInt& a,const BigInt& b){
    BigInt c(a);
    c*=b;
    return c;
}

BigInt operator/(const BigInt& a,const BigInt& b){
    BigInt c(a);
    c/=b;
    return c;
}

BigInt operator%(const BigInt& a,const BigInt& b){
    BigInt c(a);
    c%=b;
    return c;
}

BigInt pow(const BigInt& a,const BigInt& b){
    if(b.sign==-1){
        throw("ERROR");
    }
    int sign=1;
    if(a.sign==-1 && (b[0]&1)){
        sign=-1;
    }
    BigInt base=abs(a);
    BigInt exp=b;
    BigInt ans(1);
    BigInt zero("0");
    BigInt two(2);
    while(exp!=zero){
        if(exp[0]&1){
            ans*=base;
        }   
        base*=base;
        exp/=two;
    }
    ans.sign=sign;
    return ans;
}

BigInt sqrt(const BigInt& a){
    if(a.sign==-1){
        throw("ERROR");
    }
    BigInt left(1),right(a),ans("0");
    BigInt zero("0");
    BigInt two(2);
    BigInt mid=zero;
    BigInt prod;
    while(left<=right){
        mid+=left;
        mid+=right;
        mid/=two;
        prod=mid*mid;
        if(prod<=a){
            ans=mid;
            ++mid;
            left=mid;
        }
        else{
            --mid;
            right=mid;
        }
        mid=zero;
    }
    return ans;
}

BigInt &operator++(BigInt& a){
    BigInt one(1);
    a+=one;
    return a;
}

BigInt operator++(BigInt& a,int k){
    BigInt b(a);
    ++a;
    return b;
}

BigInt &operator--(BigInt& a){
    BigInt one(1);
    a-=one;
    return a;
}

BigInt operator--(BigInt& a,int k){
    BigInt b(a);
    --a;
    return b;
}

int main(){
    cout<<"Hello"<<endl;
    BigInt a(56);
    string st="-7768868768698";
    BigInt b(st);
    BigInt c("86768767576581213");
    cout<<length(c)<<endl;
    BigInt d(c);
    BigInt e("0");
    if(Null(e)){
        cout<<"true"<<endl;
    }
    else{
        cout<<"false"<<endl;
    }
    BigInt f=c;
    cout<<f[4]<<endl;
    BigInt g=-f;
    cout<<f<<endl;
    BigInt h;
    cin>>h;
    cout<<h<<endl;
    BigInt i("-96674");
    BigInt j("-296674");
    if(i>=j){
        cout<<"true"<<endl;
    }
    else{
        cout<<"false"<<endl;
    }
    vector<int> v1={4564,35,8768746,-7676,-8763543,-545,8767,88475678,34534,-87876,-7348768,-21558,254764,-575121,0,0};
    vector<int> v2={4564,870586,9897,-7676,-87676,-876876,-8767,-777065,-767654543,87876,78076,58765412,0,0,54542,-245};
    for(int x=0;x<v1.size();x++){
        BigInt b1(v1[x]);
        BigInt b2(v2[x]);
        BigInt b3=b1+b2;
        int b4=v1[x]+v2[x];
        BigInt b5=b1-b2;
        int b6=v1[x]-v2[x];
        BigInt b7=b1*b2;
        long long int b8=(((long long)v1[x])*((long long)v2[x]));
        BigInt b9("0");
        int b10=0;
        if(v2[x]!=0){
            b9=b1/b2;
            b10=v1[x]/v2[x];
        }
        cout<<"BigInt    "<<b3<<"   "<<b5<<"   "<<b7<<"   "<<b9<<endl;
        cout<<"Int       "<<b4<<"   "<<b6<<"   "<<b8<<"   "<<b10<<endl;
        cout<<endl;
    }
    BigInt k=++a;
    BigInt l=a++;
    BigInt m=--a;
    BigInt n=a--;
    cout<<k<<" "<<l<<" "<<m<<" "<<n<<" "<<a<<endl;
    BigInt o=abs(c);
    BigInt p=abs(i);
    cout<<o<<" "<<p<<endl;
    BigInt q(-5);
    BigInt r(3);
    BigInt s=pow(q,r);
    cout<<s<<endl;
    BigInt t(1);
    BigInt u=sqrt(t);
    cout<<u<<endl;
    BigInt a1=BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719")+BigInt("1625367127512635267125367125671527512635126531672567125671256125376571");
    BigInt a2=BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719")-BigInt("1625367127512635267125367125671527512635126531672567125671256125376571");
    BigInt a3=BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719")*BigInt("1625367127512635267125367125671527512635126531672567125671256125376571");
    BigInt a4=BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719")/BigInt("1625367127512635267125367125671527512635126531672567125671256125376571");
    BigInt a5=BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719")%BigInt("1625367127512635267125367125671527512635126531672567125671256125376571");
    BigInt a6=abs(BigInt("-12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719"));;
    BigInt a7=pow(BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719"),BigInt("12"));
    BigInt a8=sqrt(BigInt("12345678908765434567765456789098765456789098726523512379812351278351825387126781296129678327018273817293618531785478361498673197849823719"));
    cout<<a1<<endl;
    cout<<endl;
    cout<<a2<<endl;
    cout<<endl;
    cout<<a3<<endl;
    cout<<endl;
    cout<<a4<<endl;
    cout<<endl;
    cout<<a5<<endl;
    cout<<endl;
    cout<<a6<<endl;
    cout<<endl;
    cout<<a7<<endl;
    cout<<endl;
    cout<<a8<<endl;
}
