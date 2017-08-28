package com.cily.utils.app.pools;

import com.cily.utils.base.log.Logs;

import java.util.Arrays;

/**
 * user:cily
 * time:2017/1/24
 * desc:对象池
 */

public class Pools {
    /**注释需放开*/
    public final static int DEFAULT_POOL_SIZE = 5;
    /**
     * Interface for managing a pool of objects.
     *
     * @param <T> The pooled type.
     */
    public static interface Pool<T> {

        /**
         * @return An instance from the pool if such, null otherwise.
         */
        public T acquire();

        /**
         * Release an instance to the pool.
         *
         * @param instance The instance to release.
         * @return Whether the instance was put in the pool.
         *
         * @throws IllegalStateException If the instance is already in the pool.
         */
        public boolean release(T instance);
    }

    private Pools() {
        /* do nothing - hiding constructor */
    }

    /**
     * Simple (non-synchronized) pool of objects.
     *
     * @param <T> The pooled type.
     */
    public static class SimplePool<T> implements Pool<T> {
        //        private final Object[] mPool;
        private Object[] mPool;

        private int mPoolSize;

        /**
         * Creates a new instance.
         *
         * @param maxPoolSize The max pool size.
         *
         * @throws IllegalArgumentException If the max pool size is less than zero.
         */
        public SimplePool(int maxPoolSize) {
            if (maxPoolSize <= 0) {
//                throw new IllegalArgumentException("The max pool size must be > 0");
                maxPoolSize = DEFAULT_POOL_SIZE;
            }
            mPool = new Object[maxPoolSize];
        }

        public SimplePool(){
            mPool = new Object[DEFAULT_POOL_SIZE];
        }

        @Override
        @SuppressWarnings("unchecked")
        public T acquire() {
            if (mPoolSize > 0) {
                final int lastPooledIndex = mPoolSize - 1;
                T instance = (T) mPool[lastPooledIndex];
                mPool[lastPooledIndex] = null;
                mPoolSize--;
                return instance;
            }
            return null;
        }

        @Override
        public boolean release(T instance) {
            if (isInPool(instance)) {
//                throw new IllegalStateException("Already in the pool!");
                Logs.sysOut("The instance is already in the pool!");
                return false;
            }
            if (mPoolSize < mPool.length) {
                mPool[mPoolSize] = instance;
                mPoolSize++;
                return true;
            }else{
                mPool = Arrays.copyOf(mPool, mPool.length * 2 + 1);

            }
            return false;
        }

        private boolean isInPool(T instance) {
            for (int i = 0; i < mPoolSize; i++) {
                if (mPool[i] == instance) {
                    return true;
                }
            }
            return false;
        }

        public int size(){
            return mPoolSize;
        }
        public Object[] pools(){
            return mPool;
        }

        public void gc(){
            mPool = null;
        }
    }

    /**
     * Synchronized) pool of objects.
     *
     * @param <T> The pooled type.
     */
    public static class SynchronizedPool<T> extends SimplePool<T> {
        private final Object mLock = new Object();

        /**
         * Creates a new instance.
         *
         * @param maxPoolSize The max pool size.
         *
         * @throws IllegalArgumentException If the max pool size is less than zero.
         */
        public SynchronizedPool(int maxPoolSize) {
            super(maxPoolSize);
        }

        public SynchronizedPool(){
            super();
        }

        @Override
        public T acquire() {
            synchronized (mLock) {
                return super.acquire();
            }
        }

        @Override
        public boolean release(T element) {
            synchronized (mLock) {
                return super.release(element);
            }
        }
    }
}