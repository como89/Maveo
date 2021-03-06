package ca.qc.bdeb.maveo.modele.paroles;

/**
 * Created by WuTchanKi on 2016-11-10.
 */
/*
 * Copyright (C) 2014 Fastboot Mobile, LLC.
 *
 * This program is free software; you can redistribute it and/or modify it under the terms of the
 * GNU General Public License as published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY;
 * without even the implied warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See
 * the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program;
 * if not, see <http://www.gnu.org/licenses>.
 */


import java.net.HttpURLConnection;
import java.net.URL;


import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

/**
 * HTTP GET helper
 */
public class HttpGet {

    private static final String TAG = "HttpGet";

    /**
     * Downloads the data from the provided URL.
     *
     * @param inUrl The URL to get from
     * @param query The query field. '?' + query will be appended automatically, and the query data
     *              MUST be encoded properly.
     * @return A string with the data grabbed from the URL
     */
    public static String get(String inUrl, String query, boolean cached)
            throws Exception {
        return new String(getBytes(inUrl, query, cached));
    }

    /**
     * Downloads the data from the provided URL.
     *
     * @param inUrl The URL to get from
     * @param query The query field. '?' + query will be appended automatically, and the query data
     *              MUST be encoded properly.
     * @return A byte array of the data
     */
    public static byte[] getBytes(String inUrl, String query, boolean cached)
            throws Exception {
        final String formattedUrl = inUrl + (query.isEmpty() ? "" : ("?" + query));


        URL url = new URL(formattedUrl);
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        urlConnection.setRequestProperty("User-Agent", "OmniMusic/1.0-dev (http://www.omnirom.org)");
        urlConnection.setUseCaches(cached);
        urlConnection.setInstanceFollowRedirects(true);
        int maxStale = 60 * 60 * 24 * 28; // tolerate 4-weeks stale
        urlConnection.addRequestProperty("Cache-Control", "max-stale=" + maxStale);
        try {
            final int status = urlConnection.getResponseCode();
            // MusicBrainz returns 503 Unavailable on rate limit errors. Parse the JSON anyway.
            if (status == HttpURLConnection.HTTP_OK) {
                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
                int contentLength = urlConnection.getContentLength();
                if (contentLength <= 0) {
                    // No length? Let's allocate 100KB.
                    contentLength = 100 * 1024;
                }
                ByteArrayOutputStream baos = new ByteArrayOutputStream(contentLength);
                byte[] buffer = new byte[2048];
                BufferedInputStream bis = new BufferedInputStream(in);
                int read;

                while ((read = bis.read(buffer, 0, buffer.length)) > 0) {
                    baos.write(buffer, 0, read);
                }
                return baos.toByteArray();
            } else if (status == HttpURLConnection.HTTP_NOT_FOUND) {
                // 404
                return new byte[]{};
            } else if (status == HttpURLConnection.HTTP_FORBIDDEN) {
                return new byte[]{};
            } else if (status == HttpURLConnection.HTTP_UNAVAILABLE) {
                throw new Exception();
            } else if (status == HttpURLConnection.HTTP_MOVED_TEMP
                    || status == HttpURLConnection.HTTP_MOVED_PERM
                    || status == 307 /* HTTP/1.1 TEMPORARY REDIRECT */
                    || status == HttpURLConnection.HTTP_SEE_OTHER) {
                // We've been redirected, follow the new URL
                final String followUrl = urlConnection.getHeaderField("Location");

                return getBytes(followUrl, "", cached);
            } else {

                return new byte[]{};
            }
        } finally {
            urlConnection.disconnect();
        }
    }
}