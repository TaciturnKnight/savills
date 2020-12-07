package com.bamboo.savills.utils;

import android.util.Log;

import java.io.IOException;

import androidx.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.RequestBody;
import okio.Buffer;
import okio.BufferedSink;
import okio.ForwardingSink;
import okio.Okio;
import okio.Sink;

public class FileRequestBody extends RequestBody {
    private RequestBody requestBody;
    private long contentLength;
    private ProgressListener listener;

    public FileRequestBody(RequestBody requestBody, ProgressListener listener) {
        this.requestBody = requestBody;
        this.listener = listener;
    }

    @Nullable
    @Override
    public MediaType contentType() {
        return requestBody.contentType();
    }

    @Override
    public void writeTo(BufferedSink sink) throws IOException {
        ByteSink byteSink = new ByteSink(sink);
        BufferedSink mBufferedSink = Okio.buffer(byteSink);
        requestBody.writeTo(mBufferedSink);
        mBufferedSink.flush();
    }

    @Override
    public long contentLength() throws IOException {
        try {
            if (contentLength == 0)
                contentLength = requestBody.contentLength();
            return contentLength;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return -1;
    }

    private final class ByteSink extends ForwardingSink {
        //已上传的进度
        private long mByteLength = 0l;

        public ByteSink(Sink delegate) {
            super(delegate);
        }

        @Override
        public void write(Buffer source, long byteCount) throws IOException {
            super.write(source, byteCount);
            mByteLength += byteCount;
            double byteDouble = Double.parseDouble(mByteLength + "");
            double contentDouble = Double.parseDouble(contentLength + "");
            double progress = byteDouble / contentDouble * 100;
            int progressInt = (int) progress;
            Log.i("进度", "byteLength" + mByteLength + "content" + contentLength + "progress" + progress + "");
            listener.onProgress(progressInt);
        }
    }
}
