DROP TABLE GAMEUSER;
DROP TABLE HISTORY;

CREATE TABLE HISTORY (
    USERID VARCHAR2(15) PRIMARY KEY,
    WIN NUMBER DEFAULT 0,
    DRAW NUMBER DEFAULT 0,
    LOSE NUMBER DEFAULT 0
);

CREATE TABLE GAMEUSER (
    USERNO NUMBER PRIMARY KEY,
    USERID VARCHAR2(15) NOT NULL UNIQUE,
    USERPWD VARCHAR2(15) NOT NULL,
    USERNAME VARCHAR2(20) NOT NULL,
    PHONE VARCHAR2(11),
    ENROLLDATE DATE DEFAULT SYSDATE NOT NULL
);

DROP SEQUENCE SEQ_USERNO;
CREATE SEQUENCE SEQ_USERNO
NOCACHE;

ALTER TABLE GAMEUSER 
    ADD CONSTRAINT USER_FK FOREIGN KEY(USERID) REFERENCES HISTORY ON DELETE CASCADE;
    
CREATE OR REPLACE TRIGGER TRG_01
AFTER INSERT ON GAMEUSER
FOR EACH ROW
BEGIN
    INSERT INTO HISTORY
    VALUES(:NEW.USERID, DEFAULT, DEFAULT, DEFAULT);
END;
/

INSERT INTO GAMEUSER
VALUES(SEQ_USERNO.NEXTVAL, 'admin', 'admin', '������', '11111111111', DEFAULT);

INSERT INTO GAMEUSER
VALUES(SEQ_USERNO.NEXTVAL, 'user01', 'pass01', 'ȫ�浿', '01011111111', DEFAULT);

SELECT USERID, WIN, DRAW, LOSE
FROM GAMEUSER
JOIN HISTORY USING (USERID);

COMMIT;