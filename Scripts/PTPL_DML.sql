-- PTPL_DML.sql
-- script to populate Lending Database
-- CST 8334
-- Year: 2019
-- Last Modified: 2019-02-11
-- Aunthor: Bo Zhu
-- Modify:Yali Fu


INSERT INTO Credential_T (credentialName) VALUES ('Borrower');
INSERT INTO Credential_T (credentialName) VALUES ('Lender');
INSERT INTO Credential_T (credentialName) VALUES ('Administrator');
INSERT INTO Credential_T (credentialName) VALUES ('Moderator');

INSERT INTO Operation_T (amount, amountRepaid, dueDate, duePenalty, interest, createDate) VALUES (100000.00, 10000.00, to_date('25/03/2019','DD/MM/YYYY'), 5000.00, 10.00, to_date('21/01/2019','DD/MM/YYYY'));
INSERT INTO Operation_T (amount, amountRepaid, dueDate, duePenalty, interest, createDate) VALUES (1500000.00, 20000.00, to_date('20/03/2019','DD/MM/YYYY'), 8000.00, 20.00, to_date('21/01/2019','DD/MM/YYYY'));

INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('1', 'stanley00069', '1234abc', 'Stanley', 'Smith', to_date('21/04/1980','DD/MM/YYYY'),'stanley001@gmail.com', '613747999', '', to_date('01/12/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('2', 'an000022', '1234abc', 'Joanna', 'Lee',to_date('21/01/1976','DD/MM/YYYY'), 'joanna@hotmail.com', '6135566785', '', to_date('02/11/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('3', 'xixi0927', '1234abc', 'Bob', 'Hill',to_date('14/11/1990','DD/MM/YYYY'), 'bob9090@gmail.com', '6137332293', '', to_date('03/10/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('4', 'yang67890', '1234abc', 'Ashley' , 'Foster', to_date('16/12/1989','DD/MM/YYYY'),'ashley@gmail.com', '6132237788', '', to_date('04/09/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('1', 'frank0009', '1234abc', 'Ruby', 'Kitchener', to_date('21/03/1979','DD/MM/YYYY'),'ruby@gmial.com', '6132218989', '', to_date('05/10/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('2', 'zhu00069', '1234abc', 'Mia', 'Clark', to_date('27/05/1980','DD/MM/YYYY'),'mia', 'mia001@gmail.com', '', to_date('06/12/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('3', 'elena123', '1234abc', 'Elena' , 'Yang', to_date('21/10/1969','DD/MM/YYYY'),'elena999@gmail.com', '6132237788', '', to_date('07/12/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('4', 'bob1009', '1234abc', 'Esther', 'Kit', to_date('22/01/1991','DD/MM/YYYY'),'bob622@gmial.com', '6132208981', '', to_date('08/11/2018','DD/MM/YYYY'));
INSERT INTO User_T (credentialID, userName, pswd, firstName, lastName, DOB, email, phone, creditScore, createDate) VALUES ('1', 'qqxx1234', '1234abc', 'Mary', 'Lee', to_date('19/03/1983','DD/MM/YYYY'), 'mary999@gmail.com', '6139997878', '', to_date('09/11/2018','DD/MM/YYYY'));

INSERT INTO Opportunity_T (borrowerID, lenderID, operationID, oppoTitle, oppoDescription, status, fundAmount, expectedFundDate, createDate) VALUES ('1', '7', '1', 'Financial Dilemma at BBT', 'If approved, the merger of BBT and SunTrust would create the sixth-biggest bank in the U.S., large enough to compete against the country’s largest lenders.', 'CLOSE', 500000.00, to_date('12/01/2018','DD/MM/YYYY'), to_date('12/01/2019','DD/MM/YYYY'));
INSERT INTO Opportunity_T (borrowerID, lenderID, operationID, oppoTitle, oppoDescription, status, fundAmount, expectedFundDate, createDate) VALUES ('2', '8', '2', 'Germany Restricts Facebook’s Data Gathering', 'The competition authority said the company could no longer combine data from different sources without explicit permission from users.', 'CLOSE', 1500000.00, to_date('01/01/2019','DD/MM/YYYY'), to_date('12/01/2019','DD/MM/YYYY'));
INSERT INTO Opportunity_T (borrowerID, lenderID, operationID, oppoTitle, oppoDescription, status, fundAmount, expectedFundDate, createDate) VALUES ('3', '9', '1', 'Brexit stalemate scars prosperity, says Bank of England', 'According to the Bank of England latest assessment, the uncertainty of the last few months has left the UK in the weakest growth environment since the crisis - and those scars will linger.', 'OPEN', 500000.00, to_date('12/01/2019','DD/MM/YYYY'), to_date('12/01/2019','DD/MM/YYYY'));
INSERT INTO Opportunity_T (borrowerID, lenderID, operationID, oppoTitle, oppoDescription, status, fundAmount, expectedFundDate, createDate) VALUES ('4', '1', '2', 'OPP Title4', 'OPP Description4', 'OPEN', 1500000.00, to_date('05/01/2019','DD/MM/YYYY'), to_date('12/01/2019','DD/MM/YYYY'));
INSERT INTO Opportunity_T (borrowerID, lenderID, operationID, oppoTitle, oppoDescription, status, fundAmount, expectedFundDate, createDate) VALUES ('5', '2', '1', 'OPP Title5', 'OPP Description5', 'OPEN', 2000000.00, to_date('25/11/2019','DD/MM/YYYY'), to_date('12/01/2019','DD/MM/YYYY'));
INSERT INTO Opportunity_T (borrowerID, lenderID, operationID, oppoTitle, oppoDescription, status, fundAmount, expectedFundDate, createDate) VALUES ('6', '3', '2', 'OPP Title6', 'OPP Description6', 'PROCESSING', 1800000.00, to_date('22/10/2019','DD/MM/YYYY'), to_date('12/01/2019','DD/MM/YYYY'));

INSERT INTO Transaction_T (userID, opportunityID, amount, tran_type, transactionDate, createDate) VALUES ('1', '1', 10000.00, 'PAYEE', to_date('01/01/2019','DD/MM/YYYY'), to_date('12/01/2018','DD/MM/YYYY'));
INSERT INTO Transaction_T (userID, opportunityID, amount, tran_type, transactionDate, createDate) VALUES ('2', '2', 20000.00, 'PAYER', to_date('01/01/2019','DD/MM/YYYY'), to_date('12/01/2018','DD/MM/YYYY'));

INSERT INTO Insurance_T (insuranceNo, opportunityID, insuranceCompany, insuranceAmount, effectiveDate) VALUES ('I00001', '1', 'Insurance Company1', 10000.00, to_date('10/02/2019','DD/MM/YYYY')); 
INSERT INTO Insurance_T (insuranceNo, opportunityID, insuranceCompany, insuranceAmount, effectiveDate) VALUES ('I00002', '2', 'Insurance Company2', 12000.00, to_date('01/02/2019','DD/MM/YYYY'));




