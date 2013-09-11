package com.studiomisao.fishwarz.core;

import playn.core.Asserts;
import playn.core.Image;
import playn.core.ResourceCallback;
import playn.core.Sound;

public class KickAssetWatcher {
    /**
     * Listener interface for AssetWatcher.
     */
    public interface Listener {
        /**
         * Called when all assets are done loading (or had an error).
         */
        void done();

        /**
         * Called for each asset that failed to load.
         */
        void error(Throwable e);
    }

    private int total, loaded, errors;
    private boolean start;
    private final Listener listener;

    @SuppressWarnings("rawtypes")
    private ResourceCallback callback = new ResourceCallback() {
        @Override
        public void done(Object resource) {
            ++loaded;
            maybeDone();
        }

        @Override
        public void error(Throwable e) {
            ++errors;
            if (listener != null)
                listener.error(e);
            maybeDone();
        }
    };

    /**
     * Creates a new watcher without a listener.
     */
    public KickAssetWatcher() {
        this(null);
        start();
    }

    /**
     * Creates a new watcher with the given listener.
     * <p>
     * Note: must call {@link KickAssetWatcher#start()} after adding your resources.
     */
    public KickAssetWatcher(Listener listener) {
        this.listener = listener;
    }

    /**
     * Adds an image resource to be watched.
     */
    @SuppressWarnings("unchecked")
    public void add(Image image) {
        Asserts.checkState(!start || listener == null);
        ++total;
        image.addCallback(callback);
    }

    /**
     * Whether all resources have completed loading, either successfully or in error.
     */
    public boolean isDone() {
        return start && (loaded + errors == total);
    }

    /**
     * Done adding resources; {@link Listener#done()} will be called as soon as all assets are done
     * being loaded.
     *
     * There is no need to call this method if there is no listener. {@link #isDone()} will return
     * <code>true</code> as soon as all pending assets are loaded.
     */
    public void start() {
        start = true;
        maybeDone();
    }

    private void maybeDone() {
        if (isDone()) {
            if (listener != null) {
                listener.done();
            }
        }
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getLoaded() {
        return loaded;
    }

    public void setLoaded(int loaded) {
        this.loaded = loaded;
    }

    public int getErrors() {
        return errors;
    }

    public void setErrors(int errors) {
        this.errors = errors;
    }

    public boolean isStart() {
        return start;
    }

    public void setStart(boolean start) {
        this.start = start;
    }
}