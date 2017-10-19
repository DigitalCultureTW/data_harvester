/*
 * Copyright (C) 2017 Jonathan
 *
 * This program is free software; you can redistribute it and/or
 * modify it under the terms of the GNU General Public License
 * as published by the Free Software Foundation; either version 2
 * of the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place - Suite 330, Boston, MA  02111-1307, USA.
 */
package tw.digitalculture.data.interfaces;

/**
 *
 * @author Jonathan
 */
public abstract class Record {

    public String id;
    public String title;
    public String description;
    public String uri;
    public String filename;
    public String filetype;

    public Record(String id, String title, String description, String uri) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.uri = uri;
        setFilename();
        setFiletype();
    }

    public void setFilename() {
        this.filename = this.uri.split("/")[this.uri.split("/").length - 1];
    }

    public void setFiletype() {
        this.filetype = this.uri.split(".")[this.uri.split(".").length - 1];
    }
}
