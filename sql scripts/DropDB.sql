alter session set "_oracle_script"=true;
REVOKE ALL PRIVILEGES FROM VST_ADMIN;
DROP USER VST_ADMIN CASCADE;
COMMIT;
