t=load('terrain01.data');
x=t(:,1);
y=t(:,2);
z=t(:,3);
min(z)
max(z)
med = max (z)+ min(z);
med/=2;

z-=med;
z*=1.68;
max(z)
min(z)

new= [x,y,z];
new;
i=1;
while(i<442)
  printf("%d, %d, %d\n",new(i,1),new(i,2),new(i,3));
  i+=1;
endwhile