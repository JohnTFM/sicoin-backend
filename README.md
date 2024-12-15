## Setar banco:

```sql
CREATE EXTENSION IF NOT EXISTS postgis;

ALTER TABLE lixeira ADD COLUMN location GEOGRAPHY(Point, 4326);

CREATE OR REPLACE FUNCTION update_location()
RETURNS TRIGGER AS $$
BEGIN
    NEW.location = ST_SetSRID(ST_MakePoint(NEW.longitude, NEW.latitude), 4326);
RETURN NEW;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER set_location_on_insert_or_update
    BEFORE INSERT OR UPDATE ON lixeira
                         FOR EACH ROW
                         EXECUTE FUNCTION update_location();


```