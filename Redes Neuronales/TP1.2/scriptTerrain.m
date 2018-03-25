terr=load('terrain01.data');
x=terr(:,1);
y=terr(:,2);

min(x);
min(y);
max(x);
max(y);

s=[1:10000];
l=(s.**-2);

length(l);

hold on;
scatter(s,l);
loglog(s,l);
refresh;