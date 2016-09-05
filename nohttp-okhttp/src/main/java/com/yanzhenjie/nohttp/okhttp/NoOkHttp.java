/*
 * Copyright © Yan Zhenjie. All Rights Reserved
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.yanzhenjie.nohttp.okhttp;

import com.yolanda.nohttp.NoHttp;
import com.yolanda.nohttp.cache.Cache;
import com.yolanda.nohttp.cache.CacheEntity;
import com.yolanda.nohttp.cache.DiskCacheStore;
import com.yolanda.nohttp.download.DownloadQueue;
import com.yolanda.nohttp.rest.IParserRequest;
import com.yolanda.nohttp.rest.RequestQueue;
import com.yolanda.nohttp.rest.Response;
import com.yolanda.nohttp.rest.RestProtocol;

/**
 * Created by Yan Zhenjie on 2016/9/4.
 */
public class NoOkHttp {

    /**
     * Create a new request queue, using NoHttp default configuration. And number of concurrent requests is 3.
     *
     * @return Returns the request queue, the queue is used to control the entry of the request.
     * @see #newRequestQueue(int)
     */
    public static RequestQueue newRequestQueue() {
        return newRequestQueue(3);
    }

    /**
     * Create a new request queue, using NoHttp default configuration.
     *
     * @param threadPoolSize request the number of concurrent.
     * @return Returns the request queue, the queue is used to control the entry of the request.
     * @see #newRequestQueue()
     */
    public static RequestQueue newRequestQueue(int threadPoolSize) {
        return NoHttp.newRequestQueue(DiskCacheStore.INSTANCE, OkHttpRestConnection.getInstance(), threadPoolSize);
    }

    /**
     * Initiate a synchronization request.
     *
     * @param request request object.
     * @param <T>     {@link T}.
     * @return {@link Response}.
     * @see #startRequestSync(Cache, IParserRequest)
     */
    public static <T> Response<T> startRequestSync(IParserRequest<T> request) {
        return startRequestSync(DiskCacheStore.INSTANCE, request);
    }

    /**
     * Initiate a synchronization request.
     *
     * @param cache   cache interface, which is used to cache the request results.
     * @param request tequest object.
     * @param <T>     {@link T}.
     * @return {@link Response}.
     * @see #startRequestSync(IParserRequest)
     */
    public static <T> Response<T> startRequestSync(Cache<CacheEntity> cache, IParserRequest<T> request) {
        return NoHttp.startRequestSync(RestProtocol.getInstance(cache, OkHttpRestConnection.getInstance()), request);
    }

    /**
     * Create a new download queue, the default thread pool number is 3.
     *
     * @return {@link DownloadQueue}.
     * @see #newDownloadQueue(int)
     */
    public static DownloadQueue newDownloadQueue() {
        return newDownloadQueue(3);
    }

    /**
     * Create a new download queue.
     *
     * @param threadPoolSize thread pool number, here is the number of concurrent tasks.
     * @return {@link DownloadQueue}.
     * @see #newDownloadQueue()
     */
    public static DownloadQueue newDownloadQueue(int threadPoolSize) {
        return NoHttp.newDownloadQueue(OkHttpRestConnection.getInstance(), threadPoolSize);
    }

    private NoOkHttp() {
        NoHttp.setDefaultConnectTimeout(20 * 1000);
        NoHttp.setDefaultReadTimeout(30 * 1000);
    }

    /*
     * =================================================
     * ||                   Instance                  ||
     * =================================================
     */

    /**
     * Default thread pool size for request queue.
     */
    private static RequestQueue sRequestQueueInstance;

    /**
     * Default thread pool size for request queue.
     */
    private static DownloadQueue sDownloadQueueInstance;

    /**
     * Get default RequestQueue.
     *
     * @return {@link RequestQueue}.
     */
    public static RequestQueue getRequestQueueInstance() {
        synchronized (NoHttp.class) {
            if (sRequestQueueInstance == null) {
                sRequestQueueInstance = newRequestQueue();
            }
        }
        return sRequestQueueInstance;
    }

    /**
     * Get default DownloadQueue.
     *
     * @return {@link DownloadQueue}.
     */
    public static DownloadQueue getDownloadQueueInstance() {
        synchronized (NoHttp.class) {
            if (sDownloadQueueInstance == null) {
                sDownloadQueueInstance = newDownloadQueue();
            }
        }
        return sDownloadQueueInstance;
    }

}
