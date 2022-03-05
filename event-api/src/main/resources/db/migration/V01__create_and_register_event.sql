CREATE TABLE event (
	event_id SERIAL PRIMARY KEY,
	trans_id character varying(50) NOT NULL,
	trans_tms character varying(20) NOT NULL,
	rc_num character varying(10) NOT NULL,
	client_id character varying(10) NOT NULL,
	event_cnt INTEGER NOT NULL,
	location_cd character varying(50),
	location_id1 character varying(10),
	location_id2 character varying(10),
	addr_nbr character varying(10));

INSERT INTO event (trans_id, trans_tms, rc_num, client_id, event_cnt, location_cd, location_id1, location_id2, addr_nbr) values 
('0000abf8-d1f5-4536-8fb0-36fe934b1f28', '20151022102011927EDT', '10002', 'RPS-00001', 1, 'DESTINATION', 'T8C', '1J7', '0000000001');
