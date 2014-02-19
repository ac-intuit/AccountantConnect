Connect to the ubuntu AWS instance.
ssh -i ~/Dropbox/amazonkey/accountantconnectkey.pem 54.213.167.55 -l ubuntu

Installing mysql and tomcat7
sudo apt-get install mysql-server tomcat7

For tomcat admin
sudo apt-get install tomcat6-admin

Config files can be found in
/etc/mysql
/etc/tomcat7
/etc/default/tomcat7.

Start and stop servers using
sudo /etc/init.d/tomcat7 start|stop
sudo /etc/init.d/mysql start|stop

tomcat deployment dir is
/var/lib/tomcat7

By default only root processes can listen to portnumber < 1024. So modify IP Tables to forward 8080 to 80.
sudo /sbin/iptables -t nat -I PREROUTING -p tcp --dport 80 -j REDIRECT --to-port 8080

Include the proxyPort attribute in your HTTP connector config in server.xml:
<Connector port="8080" proxyPort="80" .../>

Command to copy the binary to the AWS instance.
scp -i ~/Dropbox/amazonkey/accountantconnectkey.pem /Users/mpanda/work/export/AccountantConnect.war ubuntu@54.213.167.55:

To deploy, copy the deploy file from the root to the AWS using the above command.
Change permission - chmod +x ./deploy
Then run
./deploy


GMAIL ID
ac.intuit42@gmail.com pwd - acintuit42

github
ac-intuit pwd - acintuit42

amazon AWS
ac.intuit42@gmail.com pwd - acintuit

server setup in local
/Users/mpanda/work/proj/Servers/apache-tomcat-7.0.50