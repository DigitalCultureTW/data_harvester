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

import java.util.List;
import java.util.function.Consumer;

/**
 *
 * @author Jonathan
 * @param <R>
 */
public abstract class Query<R> {

    public String id = this.getClass().getSimpleName();

    /**
     *
     * @param text
     * @param limit
     * @param callback
     */
//    public <R> void query(String text, Consumer<List<R>> callback);
    public abstract void query(String text, int limit, Consumer<List<R>> callback);

}
