def sendEmail():

    import smtplib
    from email.mime.multipart import MIMEMultipart
    from email.mime.text import MIMEText
    from email.mime.base import MIMEBase
    from datetime import datetime

    today = "Today.txt"
    dateNow = datetime.now()
    msg = MIMEMultipart()
    msg['From'] = 'herveselenium@gmail.com'
    msg['To'] = 'hervenyemeck@gmail.com'
    msg['Subject'] = 'Album to Listen to at Work for %02d/%02d/%04d ' % (dateNow.day, dateNow.month, dateNow.year)
    dateNow = datetime.now()
    monthNow = str(dateNow.day).encode('ascii')
    message = 'Yo Hervé. Your album for %02d/%02d/%04d is:' % (dateNow.day, dateNow.month, dateNow.year)
    msg.attach(MIMEText(message))
    filename = today
    file = open(today, "rb")
    p= MIMEBase('application', 'octet-stream')
    p.set_payload((file).read())
    p.add_header('Content-Disposition', "file; filename= %s" % filename) 
    msg.attach(p)

    mailserver = smtplib.SMTP('smtp.gmail.com',587)
    # identify ourselves to smtp gmail client
    mailserver.ehlo()
    # secure our email with tls encryption
    mailserver.starttls()
    # re-identify ourselves as an encrypted connection
    mailserver.ehlo()
    mailserver.login('herveselenium@gmail.com', 'Selen!umRul3s')

    mailserver.sendmail('herveselenium@gmail.com','hervenyemeck@gmail.com, herve.nyemeck@ansys.com',msg.as_string())

    mailserver.quit()
